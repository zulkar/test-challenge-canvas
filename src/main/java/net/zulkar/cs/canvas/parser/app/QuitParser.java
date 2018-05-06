package net.zulkar.cs.canvas.parser.app;

import net.zulkar.cs.canvas.actions.Quit;
import net.zulkar.cs.canvas.dto.Action;
import net.zulkar.cs.canvas.parser.AbstractParser;

public class QuitParser extends AbstractParser {
    public QuitParser() {
        super("Q", 0);
    }

    @Override
    protected Action parseCommand(String[] split) {
        return new Quit();
    }
}
