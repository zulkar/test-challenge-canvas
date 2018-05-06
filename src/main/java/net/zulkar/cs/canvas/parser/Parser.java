package net.zulkar.cs.canvas.parser;

import net.zulkar.cs.canvas.actions.ErrorAction;
import net.zulkar.cs.canvas.dto.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Parser {
    private static final Logger log = LogManager.getLogger(Parser.class);
    private final List<ActionParser> parsers;

    public Parser(List<ActionParser> parsers) {
        this.parsers = parsers;
    }

    public Action parse(String raw) {
        List<Action> result = new ArrayList<>();
        try {
            result = parsers.stream()
                    .map(p -> p.parse(raw))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Exception parsing {}", raw, e);
            return new ErrorAction("Exception when parsing the action: " + e);
        }

        if (result.size() > 1) {
            log.error("More than one action match command {} : {} ", raw, result);
            return new ErrorAction("More than one action match command " + raw + " : " + result);
        }

        if (result.isEmpty()) {
            log.error("Unknown command: {}", raw);
            return new ErrorAction("Unknown command: " + raw);
        }
        log.info("Command {} parsed as {} ", raw, result);
        return result.get(0);

    }
}
