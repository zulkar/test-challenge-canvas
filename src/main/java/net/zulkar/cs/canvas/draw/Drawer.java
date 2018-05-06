package net.zulkar.cs.canvas.draw;

import net.zulkar.cs.canvas.dto.Vector;

import java.util.ArrayDeque;
import java.util.Deque;

public class Drawer {
    private final Deque<DrawAction> actions = new ArrayDeque<>();
    private final Canvas canvas;


    public Drawer(Canvas canvas) {
        this.canvas = canvas;
    }

    public Drawer(Vector size) {
        this.canvas = new Canvas(size);
    }

    public void addAction(DrawAction action) {
        action.validate(canvas);
        actions.addLast(action);
        action.apply(canvas);
    }

    public void invalidate() {
        actions.forEach(a -> a.apply(canvas));
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
