package net.zulkar.cs.canvas.draw.actions;

import net.zulkar.cs.canvas.draw.Canvas;
import net.zulkar.cs.canvas.dto.Vector;
import org.junit.Assert;
import org.junit.Test;

import static net.zulkar.cs.CommonTestUtils.createCanvas;

public class FillTest {
    private static final Vector R_V1 = new Vector(1, 2);
    private static final Vector R_V2 = new Vector(4, 5);

    @Test
    public void fillInsideTest() {
        Canvas c = givenCanvasWithRect();

        Fill fill = new Fill(new Vector(2, 3), '0');

        fill.validate(c);
        fill.apply(c);

        Assert.assertEquals(createCanvas(
                "          ",
                "          ",
                " XXXX     ",
                " X00X     ",
                " X00X     ",
                " XXXX     "
                ).toString(),
                c.toString());

    }

    @Test
    public void fillBorderTest() {
        Canvas c = givenCanvasWithRect();

        Fill fill = new Fill(R_V1, '0');

        fill.validate(c);
        fill.apply(c);

        Assert.assertEquals(createCanvas(
                "          ",
                "          ",
                " 0000     ",
                " 0  0     ",
                " 0  0     ",
                " 0000     "
                ).toString(),
                c.toString());

    }

    @Test
    public void fillSameColorTest() {
        Canvas c = givenCanvasWithRect();

        Fill fill = new Fill(new Vector(2, 3), 'X');

        fill.validate(c);
        fill.apply(c);

        Assert.assertEquals(createCanvas(
                "          ",
                "          ",
                " XXXX     ",
                " XXXX     ",
                " XXXX     ",
                " XXXX     "
                ).toString(),
                c.toString());

    }


    @Test
    public void fillInsideWithoutCorderBoundaries() {
        Canvas c = givenCanvasWithRect();
        c.setColor(R_V1, Canvas.SPACE);

        Fill fill = new Fill(new Vector(2, 3), '0', false);

        fill.validate(c);
        fill.apply(c);

        Assert.assertEquals(createCanvas(
                "0000000000",
                "0000000000",
                "00XXX00000",
                "0X00X00000",
                "0X00X00000",
                "0XXXX00000"
                ).toString(),
                c.toString());

    }

    private Canvas givenCanvasWithRect() {
        Canvas c = new Canvas(10, 6);
        Rectangle rectangle = new Rectangle(R_V1, R_V2, 'X');
        rectangle.validate(c);
        rectangle.apply(c);
        return c;
    }
}