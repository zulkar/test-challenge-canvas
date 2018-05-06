package net.zulkar.cs.canvas.draw.actions;

import net.zulkar.cs.canvas.Validation;
import net.zulkar.cs.canvas.draw.Canvas;
import net.zulkar.cs.canvas.draw.DrawAction;
import net.zulkar.cs.canvas.dto.Vector;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class Line implements DrawAction {
    private final Vector v1;
    private final Vector v2;
    private final char color;

    public Line(Vector v1, Vector v2, char color) {
        this.v1 = v1;
        this.v2 = v2;
        this.color = color;
    }

    @Override
    public void validate(Canvas canvas) {
        Validation.validateInside(v1, canvas.getSize());
        Validation.validateInside(v2, canvas.getSize());

        if ((v1.getX() != v2.getX())
                &&
                (v1.getY() != v2.getY())) {
            throw new IllegalArgumentException("Only horizontal or vertical lines are supported");
        }

    }

    @Override
    public void apply(Canvas canvas) {
        Vector first = new Vector(Math.min(v1.getX(), v2.getX()), Math.min(v1.getY(), v2.getY()));
        Vector second = new Vector(Math.max(v1.getX(), v2.getX()), Math.max(v1.getY(), v2.getY()));

        for (int x = first.getX(); x <= second.getX(); x++) {
            for (int y = first.getY(); y <= second.getY(); y++) {
                canvas.setColor(new Vector(x, y), color);
            }
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("v1", v1)
                .append("v2", v2)
                .append("color", color)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return color == line.color &&
                Objects.equals(v1, line.v1) &&
                Objects.equals(v2, line.v2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(v1, v2, color);
    }
}
