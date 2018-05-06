package net.zulkar.cs.canvas.parser.draw;

import net.zulkar.cs.canvas.draw.actions.Rectangle;
import net.zulkar.cs.canvas.dto.Action;
import net.zulkar.cs.canvas.dto.Vector;
import net.zulkar.cs.canvas.parser.AbstractIntParametrizedActionParser;

public class RectangleParser extends AbstractIntParametrizedActionParser {

    private static final char color = 'x';

    public RectangleParser() {
        super("R", 4);
    }

    @Override
    protected Action parseCommand(String command, int[] params) {
        return new Rectangle(new Vector(params[0], params[1]).shift(OFFSET), new Vector(params[2], params[3]).shift(OFFSET), color);
    }
}
