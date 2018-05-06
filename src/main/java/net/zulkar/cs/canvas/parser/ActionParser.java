package net.zulkar.cs.canvas.parser;

import net.zulkar.cs.canvas.dto.Action;

public interface ActionParser {
    public static final String PARAMETER_SEPARATOR = " ";

    Action parse(String raw);
}
