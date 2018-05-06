package net.zulkar.cs.canvas.draw.actions;

import net.zulkar.cs.canvas.draw.Canvas;
import net.zulkar.cs.canvas.dto.Vector;
import org.junit.Assert;
import org.junit.Test;

import static net.zulkar.cs.CommonTestUtils.createCanvas;

public class RectangleTest {

    @Test
    public void shouldDrawRectangle() {
        Canvas c = new Canvas(10, 6);
        Rectangle rectangle = new Rectangle(new Vector(1, 2), new Vector(4, 5), 'X');
        rectangle.validate(c);
        rectangle.apply(c);


        Assert.assertEquals(createCanvas(
                "          ",
                "          ",
                " XXXX     ",
                " X  X     ",
                " X  X     ",
                " XXXX     "
                ).toString(),
                c.toString());
    }

}