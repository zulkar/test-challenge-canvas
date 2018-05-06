package net.zulkar.cs.canvas.render;

import net.zulkar.cs.canvas.draw.Canvas;
import net.zulkar.cs.canvas.draw.DrawAction;
import net.zulkar.cs.canvas.draw.Drawer;
import net.zulkar.cs.canvas.draw.actions.Fill;
import net.zulkar.cs.canvas.draw.actions.Line;
import net.zulkar.cs.canvas.draw.actions.Rectangle;
import net.zulkar.cs.canvas.dto.Vector;
import net.zulkar.cs.canvas.parser.AbstractParser;
import org.junit.Test;

import static net.zulkar.cs.CommonTestUtils.toUnixEOL;
import static org.junit.Assert.assertEquals;

public class StringStandardRenderTest {

    private StringStandardRender stringStandardRender = new StringStandardRender();
    private Drawer drawer = new Drawer(new Canvas(20, 4));


    @Test
    public void acceptaneSmokeTestRender() {
        assertRender(
                "----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------");

        withCommand(new Line(new Vector(1, 2).shift(AbstractParser.OFFSET), new Vector(6, 2).shift(AbstractParser.OFFSET), 'x'));
        assertRender(
                "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------");

        withCommand(new Line(new Vector(6, 3).shift(AbstractParser.OFFSET), new Vector(6, 4).shift(AbstractParser.OFFSET), 'x'));
        assertRender(
                "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|     x              |\n" +
                        "|     x              |\n" +
                        "----------------------");

        withCommand(new Rectangle(new Vector(14, 1).shift(AbstractParser.OFFSET), new Vector(18, 3).shift(AbstractParser.OFFSET), 'x'));
        assertRender(
                "----------------------\n" +
                        "|             xxxxx  |\n" +
                        "|xxxxxx       x   x  |\n" +
                        "|     x       xxxxx  |\n" +
                        "|     x              |\n" +
                        "----------------------");
        withCommand(new Fill(new Vector(10, 3).shift(AbstractParser.OFFSET), 'o'));
        assertRender(
                "----------------------\n" +
                        "|oooooooooooooxxxxxoo|\n" +
                        "|xxxxxxooooooox   xoo|\n" +
                        "|     xoooooooxxxxxoo|\n" +
                        "|     xoooooooooooooo|\n" +
                        "----------------------");
    }

    private void withCommand(DrawAction action) {
        drawer.addAction(action);
    }

    private void assertRender(String expected) {
        stringStandardRender.render(drawer);
        String actual = stringStandardRender.getString();
        assertEquals(toUnixEOL(expected), toUnixEOL(actual));
    }

}