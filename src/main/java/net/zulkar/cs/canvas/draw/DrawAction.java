package net.zulkar.cs.canvas.draw;

import net.zulkar.cs.canvas.ActionsProcessor;
import net.zulkar.cs.canvas.dto.Action;

public interface DrawAction extends Action {
    void validate(Canvas canvas);

    void apply(Canvas canvas);

    @Override
    default Type getType() {
        return Type.DRAW;
    }

    @Override
    default void execute(ActionsProcessor actionsProcessor) {
        actionsProcessor.getDrawer().addAction(this);
    }
}
