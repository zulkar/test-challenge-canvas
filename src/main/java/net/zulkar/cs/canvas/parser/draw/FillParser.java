package net.zulkar.cs.canvas.parser.draw;

import net.zulkar.cs.canvas.actions.ErrorAction;
import net.zulkar.cs.canvas.draw.actions.Fill;
import net.zulkar.cs.canvas.dto.Action;
import net.zulkar.cs.canvas.dto.Vector;
import net.zulkar.cs.canvas.parser.AbstractParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class FillParser extends AbstractParser {
    private static final Logger log = LogManager.getLogger(FillParser.class);

    public FillParser() {
        super("B", 3);
    }

    @Override
    protected Action parseCommand(String[] split) {
        try {
            int[] params = Arrays.stream(split).skip(1).limit(2).mapToInt(Integer::valueOf).toArray();
            String color = split[split.length - 1];
            if (color.length() != 1) {
                return new ErrorAction("Color should be 1 char");
            }
            return new Fill(new Vector(params[0], params[1]).shift(OFFSET), color.charAt(0));
        } catch (NumberFormatException e) {
            log.error("Bad number format", e);
            return new ErrorAction("Cannot parse int value: " + e.getMessage());
        }
    }
}
