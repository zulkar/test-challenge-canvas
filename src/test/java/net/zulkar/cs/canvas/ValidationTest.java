package net.zulkar.cs.canvas;

import net.zulkar.cs.canvas.dto.Vector;
import org.junit.Test;

public class ValidationTest {

    @Test
    public void validateInsideSuccess() {
        Validation.validateInside(new Vector(1, 1), new Vector(2, 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateInsideBorder() {
        Validation.validateInside(new Vector(1, 3), new Vector(2, 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateInsideNegative() {
        Validation.validateInside(new Vector(-1, 2), new Vector(2, 3));
    }

    @Test
    public void validateNonNegative() {
        Validation.validateNonNegative(new Vector(1, 1));
        Validation.validateNonNegative(new Vector(0, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateNonNegativeFail() {
        Validation.validateNonNegative(new Vector(-1, 1));
    }

    @Test
    public void validatePositive() {
        Validation.validatePositive(new Vector(1, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validatePositiveFail() {
        Validation.validatePositive(new Vector(0, 0));
    }
}