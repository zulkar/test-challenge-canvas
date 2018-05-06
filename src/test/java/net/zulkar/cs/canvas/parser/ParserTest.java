package net.zulkar.cs.canvas.parser;

import net.zulkar.cs.canvas.actions.ErrorAction;
import net.zulkar.cs.canvas.dto.Action;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParserTest {

    @Mock
    private ActionParser parser1;

    @Mock
    private ActionParser parser2;

    @Mock
    private Action action1;

    @Mock
    private Action action2;


    private Parser parser;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        parser = new Parser(Arrays.asList(parser1, parser2));
        when(action1.toString()).thenReturn("MOCK ACTION 1");
        when(action2.toString()).thenReturn("MOCK ACTION 2");

    }


    @Test
    public void shouldReturnErrorIfTwoParsersReturnedAction() {
        givenParserReturn(parser1, action1);
        givenParserReturn(parser2, action2);
        Action action = parser.parse("TEST");
        Assert.assertEquals(new ErrorAction("More than one action match command TEST : [MOCK ACTION 1, MOCK ACTION 2]"), action);

    }

    @Test
    public void shouldReturnErrorIfNoParsersReturnedAction() {
        givenParserReturn(parser1, null);
        givenParserReturn(parser2, null);
        Action action = parser.parse("TEST");
        assertEquals(new ErrorAction("Unknown command: TEST"), action);

    }

    @Test
    public void shouldReturnActionIfOnlyOneParsersReturnedAction() {
        Action expected = mock(Action.class);
        givenParserReturn(parser1, expected);
        givenParserReturn(parser2, null);
        Action returned = parser.parse("TEST");
        assertSame(expected, returned);

    }

    @Test
    public void shouldReturnErrorActionWhenParserThrewException() {
        when(parser1.parse(any())).thenThrow(new RuntimeException("Exception Test"));
        Action returned = parser.parse("TEST");
        assertEquals(new ErrorAction("Exception when parsing the action: java.lang.RuntimeException: Exception Test"), returned);

    }

    private void givenParserReturn(ActionParser actionParser, Action action) {
        when(actionParser.parse(any())).thenReturn(action);
    }
}