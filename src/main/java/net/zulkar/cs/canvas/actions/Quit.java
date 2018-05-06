package net.zulkar.cs.canvas.actions;

import net.zulkar.cs.canvas.ActionsProcessor;
import net.zulkar.cs.canvas.dto.Action;

public class Quit implements Action {

    @Override
    public void execute(ActionsProcessor actionsProcessor) {
        actionsProcessor.quit();
    }

    @Override
    public Type getType() {
        return Type.APPLICATION;
    }
}
