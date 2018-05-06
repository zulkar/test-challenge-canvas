package net.zulkar.cs.canvas.parser.app;

import net.zulkar.cs.canvas.actions.CreateCanvas;
import net.zulkar.cs.canvas.dto.Action;
import net.zulkar.cs.canvas.dto.Vector;
import net.zulkar.cs.canvas.parser.AbstractIntParametrizedActionParser;

public class CreateCanvasParser extends AbstractIntParametrizedActionParser {
    public CreateCanvasParser() {
        super("C", 2);
    }

    @Override
    protected Action parseCommand(String command, int[] params) {
        return new CreateCanvas(new Vector(params[0], params[1]));
    }
}