package net.zulkar.cs.canvas.dto;

import net.zulkar.cs.canvas.ActionsProcessor;

public interface Action {
    void execute(ActionsProcessor actionsProcessor);

    Type getType();

    enum Type {
        DRAW,
        APPLICATION
    }
}
