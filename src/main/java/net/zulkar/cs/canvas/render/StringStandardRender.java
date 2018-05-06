package net.zulkar.cs.canvas.render;


import net.zulkar.cs.canvas.draw.Canvas;
import net.zulkar.cs.canvas.draw.Drawer;
import net.zulkar.cs.canvas.dto.Vector;

public class StringStandardRender implements Renderer {


    private StringBuilder builder = new StringBuilder();

    @Override
    public void print(String desc) {
        builder.append(desc);
    }

    @Override
    public void println(String desc) {
        builder.append(desc).append(System.lineSeparator());
    }

    @Override
    public void close() {

    }

    @Override
    public void render(Drawer drawer) {
        builder = new StringBuilder();
        builder.setLength(0);
        Canvas canvas = drawer.getCanvas();
        Vector size = canvas.getSize();

        size.shift(2, 2).forEachDot((i, j) -> {
            builder.append(getCharAt(i, j, canvas));
            if (i == size.getX() + 1 && j != size.getY() + 1) {
                builder.append(System.lineSeparator());
            }
        });
    }

    private char getCharAt(int i, int j, Canvas canvas) {
        if (j == 0 || j == canvas.getSize().getY() + 1) {
            return '-';
        }
        if (i == 0 || i == canvas.getSize().getX() + 1) {
            return '|';
        }
        return canvas.getColor(new Vector(i - 1, j - 1));
    }

    public String getString() {
        return builder.toString();
    }
}
