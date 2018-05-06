package net.zulkar.cs.canvas;

import net.zulkar.cs.canvas.actions.ErrorAction;
import net.zulkar.cs.canvas.command.CommandSupplier;
import net.zulkar.cs.canvas.draw.Drawer;
import net.zulkar.cs.canvas.dto.Action;
import net.zulkar.cs.canvas.parser.Parser;
import net.zulkar.cs.canvas.render.Renderer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ActionsProcessor {
    private static final Logger log = LogManager.getLogger(ActionsProcessor.class);
    private CommandSupplier commandSupplier;
    private Parser parser;
    private Renderer renderer;
    private Drawer drawer;

    private boolean completed = false;
    private boolean initialized = false;


    public void init(CommandSupplier commandSupplier, Parser parser, Renderer renderer) {
        this.commandSupplier = commandSupplier;
        this.parser = parser;
        this.renderer = renderer;
        completed = false;
        initialized = true;
    }

    public void run() {
        if (!initialized) {
            log.error("ActionsProcessor is not initialized");
            throw new IllegalStateException("Init application first!");
        }

        while (!completed) {
            Optional<String> command = commandSupplier.getNextCommand();
            if (!command.isPresent()) {
                log.error("No new commands, exiting");
                break;
            } else {
                process(command.get());
            }
        }
        renderer.close();
    }

    private void process(String command) {
        log.info("entered command {}", command);
        Action action = parser.parse(command);
        if (action.getType() == Action.Type.DRAW && drawer == null) {
            log.error("Canvas is not initialized");
            new ErrorAction("Canvas is not initialized").execute(this);
        } else {
            log.info("executing command {} as {}", command, action);
            try {
                action.execute(this);
            } catch (Exception e) {
                log.error("executing command {} as ", command, action);
                new ErrorAction("Error while executing command: " + command + " " + e.getMessage()).execute(this);
            }

        }
        if (drawer != null && !completed) {
            renderer.render(drawer);
        }
    }

    public void quit() {
        log.error("Quit");
        completed = true;
    }

    public Drawer getDrawer() {
        return drawer;
    }

    public void setDrawer(Drawer drawer) {
        log.error("Drawer set");
        this.drawer = drawer;
    }

    public Renderer getRenderer() {
        return renderer;
    }
}
