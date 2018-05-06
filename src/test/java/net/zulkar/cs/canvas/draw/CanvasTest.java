package net.zulkar.cs.canvas.draw;

import net.zulkar.cs.canvas.dto.Vector;
import org.junit.Assert;
import org.junit.Test;

import static net.zulkar.cs.CommonTestUtils.createCanvas;
import static org.junit.Assert.assertEquals;

public class CanvasTest {

    @Test
    public void testPoint() {
        Canvas c = new Canvas(4, 3);
        c.setColor(new Vector(1, 2), 'X');
        Canvas expected = createCanvas(
                "    ",
                "    ",
                " X  ");
        assertEquals(expected.toString(), c.toString());
    }

    @Test
    public void testPoint2() {
        Canvas c = new Canvas(3, 5);
        c.setColor(new Vector(1, 2), 'X');
        Canvas expected = createCanvas(
                "   ",
                "   ",
                " X ",
                "   ",
                "   ");
        assertEquals(expected, c);
    }

    @Test
    public void testClear() {
        Canvas c = createCanvas(
                "X  ",
                " X ",
                "   ");

        c.clearCanvas();

        Assert.assertEquals(createCanvas(
                "   ",
                "   ",
                "   "), c);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotSetPointAtBorder() {
        Canvas c = new Canvas(3, 3);
        c.setColor(new Vector(3, 0), 'X');
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowNegativeArgs() {
        Canvas c = new Canvas(3, 3);
        c.setColor(new Vector(-1, 0), 'X');
    }

}