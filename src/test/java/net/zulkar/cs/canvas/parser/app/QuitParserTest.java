package net.zulkar.cs.canvas.parser.app;

import net.zulkar.cs.canvas.actions.ErrorAction;
import net.zulkar.cs.canvas.actions.Quit;
import net.zulkar.cs.canvas.dto.Action;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class QuitParserTest {

    @Test
    public void shouldParseQuit() {
        Action action = new QuitParser().parse("Q");
        assertTrue(Quit.class.isAssignableFrom(action.getClass()));
    }

    @Test
    public void shouldNotAllowParams() {
        Action action = new QuitParser().parse("Q q");
        assertTrue(ErrorAction.class.isAssignableFrom(action.getClass()));
    }

    @Test
    public void shouldNotParseNotQuit() {
        Action action = new QuitParser().parse("A");
        assertNull(action);
    }

    @Test
    public void shouldNotParseEmpty() {
        assertNull(new QuitParser().parse(""));

    }

    @Test
    public void shouldNotParseNull() {
        assertNull(new QuitParser().parse(null));
    }

}