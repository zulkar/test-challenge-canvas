package net.zulkar.cs.canvas.draw;

import net.zulkar.cs.canvas.Validation;
import net.zulkar.cs.canvas.dto.Vector;

import java.util.Arrays;
import java.util.Objects;

public class Canvas {
    public static final char SPACE = ' ';
    private final char[] canvas;
    private final Vector size;

    public Canvas(Vector size) {
        this.size = size;
        canvas = new char[size.getX() * size.getY()];
        clearCanvas();
    }

    public Canvas(int x, int y) {
        this(new Vector(x, y));
    }

    public final void clearCanvas() {
        for (int i = 0; i < canvas.length; i++) {
            canvas[i] = SPACE;
        }
    }

    public Vector getSize() {
        return size;
    }

    private int at(Vector point) {
        return size.getX() * point.getY() + point.getX();
    }

    public char getColor(Vector point) {
        Validation.validateInside(point, size);
        return canvas[at(point)];
    }

    public void setColor(Vector point, char newColor) {
        Validation.validateInside(point, size);
        canvas[at(point)] = newColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Canvas canvas1 = (Canvas) o;
        return Arrays.equals(canvas, canvas1.canvas) &&
                Objects.equals(size, canvas1.size);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(canvas);
        return result;
    }


    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (int j = 0; j < size.getY(); j++) {
            text.append(">").append(new String(canvas, size.getX() * j, size.getX())).append("<\r\n");
        }

        return "Canvas =" + size + "\r\n" +
                "\r\n" +
                text.toString() +
                "";
    }
}
