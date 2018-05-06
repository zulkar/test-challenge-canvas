package net.zulkar.cs;


import net.zulkar.cs.canvas.draw.Canvas;
import net.zulkar.cs.canvas.dto.Vector;

public final class CommonTestUtils {
    private CommonTestUtils() {
    }


    public static Canvas createCanvas(String... lines) {
        Canvas canvas = new Canvas(lines[0].length(), lines.length);
        canvas.getSize().forEachDot(
                (i, j) -> canvas.setColor(new Vector(i, j),
                        lines[j].charAt(i))
        );
        return canvas;
    }

    public static String toUnixEOL(String actual) {
        return actual.replace("\r?\n", "\n");
    }

}
