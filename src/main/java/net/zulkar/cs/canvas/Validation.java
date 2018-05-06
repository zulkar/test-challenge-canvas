package net.zulkar.cs.canvas;

import net.zulkar.cs.canvas.dto.Vector;

public final class Validation {
    private Validation() {
    }

    public static void validateInside(Vector point, Vector border) {
        validatePositive(border);
        validateNonNegative(point);

        if (!point.lessThan(border)) {
            throw new IllegalArgumentException("Out of range: " + point + " should be less than " + border);
        }
    }

    public static void validateNonNegative(Vector point) {
        if (point.getX() < 0 || point.getY() < 0) {
            throw new IllegalArgumentException("Dimensions cannot be negative, got " + point);
        }
    }

    public static void validatePositive(Vector point) {
        if (!isPositive(point)) {
            throw new IllegalArgumentException("Dimensions cannot be negative or zero, got " + point);
        }
    }

    public static boolean isPositive(Vector point) {
        return point.getX() > 0 && point.getY() > 0;
    }

    public static boolean isNegative(Vector point) {
        return point.getX() < 0 || point.getY() < 0;
    }
}

