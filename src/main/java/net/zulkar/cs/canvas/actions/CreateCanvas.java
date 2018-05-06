package net.zulkar.cs.canvas.actions;

import net.zulkar.cs.canvas.ActionsProcessor;
import net.zulkar.cs.canvas.draw.Drawer;
import net.zulkar.cs.canvas.dto.Action;
import net.zulkar.cs.canvas.dto.Vector;

import java.util.Objects;

public class CreateCanvas implements Action {

    private final Vector size;

    public CreateCanvas(Vector size) {
        this.size = size;
    }

    @Override
    public void execute(ActionsProcessor actionsProcessor) {
        actionsProcessor.setDrawer(new Drawer(size));
    }

    @Override
    public Type getType() {
        return Type.APPLICATION;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateCanvas that = (CreateCanvas) o;
        return Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {

        return Objects.hash(size);
    }
}
