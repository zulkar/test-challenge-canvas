package net.zulkar.cs.canvas.dto;

import net.zulkar.cs.canvas.IntBiConsumer;

import java.util.Objects;

public class Vector {
    private final int x;
    private final int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return x == vector.x &&
                y == vector.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x + "x" + y;
    }

    public boolean lessThan(Vector dimensions) {
        return x < dimensions.x && y < dimensions.y;
    }

    /**
     * process every dot, starting from 0-0 over every column and then over every row to the x, y
     *
     * @param consumer
     */
    public void forEachDot(IntBiConsumer consumer) {
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                consumer.consume(i, j);
            }
        }
    }

    public Vector shift(int x, int y) {
        return new Vector(this.x + x, this.y + y);
    }

    public Vector shift(Vector offset) {
        return new Vector(this.x + offset.x, this.y + offset.y);
    }
}
