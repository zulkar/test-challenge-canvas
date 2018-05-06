package net.zulkar.cs.canvas.parser.draw;

import net.zulkar.cs.canvas.actions.ErrorAction;
import net.zulkar.cs.canvas.draw.actions.Rectangle;
import net.zulkar.cs.canvas.dto.Action;
import net.zulkar.cs.canvas.dto.Vector;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RectangleParserTest {

    @Test
    public void shouldParseRectangle() {
        Action action = new RectangleParser().parse("R 1 2 3 4");
        Assert.assertEquals(new Rectangle(new Vector(0, 1), new Vector(2, 3), 'x'), action);

    }

    @Test
    public void shouldNotParseRectangleIfNotRectangle() {
        Action action = new RectangleParser().parse("A 1 2 c");
        assertNull(action);

    }

    @Test
    public void shouldParseRectangleWithErrorIfParamWrong() {
        Action action = new RectangleParser().parse("R 1 2 one two");
        Assert.assertEquals(new ErrorAction("Cannot parse int value: For input string: \"one\""), action);

    }

    @Test
    public void shouldParseRectangleWithErrorIfNotEnoughParams() {
        Action action = new RectangleParser().parse("R 1");
        assertEquals(new ErrorAction("Expected 4 params for command R but got 1"), action);
    }

    @Test
    public void shouldParseRectangleWithErrorIfParamsNotSplitted() {
        Action action = new RectangleParser().parse("R 1234");
        assertEquals(new ErrorAction("Expected 4 params for command R but got 1"), action);
    }

    @Test
    public void shouldNotParseEmpty() {
        assertNull(new RectangleParser().parse(""));

    }

    @Test
    public void shouldNotParseNull() {
        assertNull(new RectangleParser().parse(null));
    }

}