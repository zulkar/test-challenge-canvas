package net.zulkar.cs.canvas.parser;

import net.zulkar.cs.canvas.actions.ErrorAction;
import net.zulkar.cs.canvas.dto.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public abstract class AbstractIntParametrizedActionParser extends AbstractParser {
    private static final Logger log = LogManager.getLogger(AbstractIntParametrizedActionParser.class);

    protected AbstractIntParametrizedActionParser(String command, int paramCount) {
        super(command, paramCount);
    }

    @Override
    protected Action parseCommand(String[] split) {
        try {
            int[] params = Arrays.stream(split).skip(1).mapToInt(Integer::valueOf).toArray();

            return parseCommand(split[0], params);
        } catch (NumberFormatException e) {
            log.error("Bad number format", e);
            return new ErrorAction("Cannot parse int value: " + e.getMessage());
        }

    }


    protected abstract Action parseCommand(String command, int[] params);
}
