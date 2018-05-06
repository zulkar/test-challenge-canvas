package net.zulkar.cs.canvas.parser.draw;

import net.zulkar.cs.canvas.actions.ErrorAction;
import net.zulkar.cs.canvas.draw.actions.Line;
import net.zulkar.cs.canvas.dto.Action;
import net.zulkar.cs.canvas.dto.Vector;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LineParserTest {

    @Test
    public void shouldParseLine() {
        Action action = new LineParser().parse("L 1 2 3 4");
        Assert.assertEquals(new Line(new Vector(0, 1), new Vector(2, 3), 'x'), action);

    }

    @Test
    public void shouldNotParseLineIfNotLine() {
        Action action = new LineParser().parse("A 1 2 c");
        assertNull(action);

    }

    @Test
    public void shouldParseLineWithErrorIfParamWrong() {
        Action action = new LineParser().parse("L 1 2 one two");
        Assert.assertEquals(new ErrorAction("Cannot parse int value: For input string: \"one\""), action);

    }

    @Test
    public void shouldParseLineWithErrorIfNotEnoughParams() {
        Action action = new LineParser().parse("L 1");
        assertEquals(new ErrorAction("Expected 4 params for command L but got 1"), action);
    }

    @Test
    public void shouldParseLineWithErrorIfParamsNotSplitted() {
        Action action = new LineParser().parse("L 1234");
        assertEquals(new ErrorAction("Expected 4 params for command L but got 1"), action);
    }

    @Test
    public void shouldNotParseEmpty() {
        assertNull(new LineParser().parse(""));

    }

    @Test
    public void shouldNotParseNull() {
        assertNull(new LineParser().parse(null));
    }

}