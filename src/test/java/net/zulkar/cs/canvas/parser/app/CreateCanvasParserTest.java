package net.zulkar.cs.canvas.parser.app;

import net.zulkar.cs.canvas.actions.CreateCanvas;
import net.zulkar.cs.canvas.actions.ErrorAction;
import net.zulkar.cs.canvas.dto.Action;
import net.zulkar.cs.canvas.dto.Vector;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class CreateCanvasParserTest {

    @Test
    public void shouldParseCreateCanvas() {
        Action action = new CreateCanvasParser().parse("C 10 20");
        Assert.assertEquals(new CreateCanvas(new Vector(10, 20)), action);
    }

    @Test
    public void shouldNotAllowNoParams() {
        Action action = new CreateCanvasParser().parse("C 10");
        Assert.assertEquals(new ErrorAction("Expected 2 params for command C but got 1"), action);
    }

    @Test
    public void shouldNotParseNotCanvas() {
        Action action = new CreateCanvasParser().parse("A");
        assertNull(action);
    }

    @Test
    public void shouldNotParseEmpty() {
        assertNull(new CreateCanvasParser().parse(""));

    }

    @Test
    public void shouldNotParseNull() {
        assertNull(new CreateCanvasParser().parse(null));
    }

}