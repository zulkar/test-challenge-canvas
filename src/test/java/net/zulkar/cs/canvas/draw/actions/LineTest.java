package net.zulkar.cs.canvas.draw.actions;

import net.zulkar.cs.canvas.draw.Canvas;
import net.zulkar.cs.canvas.dto.Vector;
import org.junit.Assert;
import org.junit.Test;

import static net.zulkar.cs.CommonTestUtils.createCanvas;

public class LineTest {

    private Canvas canvas;
    private Line line;

    @Test
    public void shouldDrawHorizontalLine() {
        givenCanvas();
        Line line = new Line(new Vector(1, 2), new Vector(3, 2), 'x');
        line.apply(canvas);
        Assert.assertEquals(createCanvas(
                "     ",
                "     ",
                " xxx ").toString(),
                canvas.toString());

    }


    @Test
    public void shouldDrawVerticalLine() {
        givenCanvas();
        line = new Line(new Vector(1, 0), new Vector(1, 2), 'x');
        whenApplyAction();
        Assert.assertEquals(createCanvas(
                " x   ",
                " x   ",
                " x   ").toString(),
                canvas.toString());

    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldNotDrawLineOutOfBorderY() {
        givenCanvas();
        Line line = new Line(new Vector(1, 4), new Vector(2, 4), 'x');
        line.validate(canvas);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotDrawLineOutOfBorderX() {
        givenCanvas();
        Line line = new Line(new Vector(2, 1), new Vector(10, 1), 'x');
        line.validate(canvas);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotDrawInclined() {
        givenCanvas();
        Line line = new Line(new Vector(1, 1), new Vector(2, 2), 'x');
        line.validate(canvas);
    }


    private void givenCanvas() {
        canvas = new Canvas(5, 3);
    }

    private void whenApplyAction() {
        line.validate(canvas);
        line.apply(canvas);
    }

}