package net.zulkar.cs.canvas.render;

import net.zulkar.cs.canvas.draw.Drawer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleRenderer implements Renderer {
    private static final Logger log = LogManager.getLogger(ConsoleRenderer.class);

    private final StringStandardRender render;

    public ConsoleRenderer() {
        render = new StringStandardRender();
    }

    @Override
    public void print(String desc) {
        System.out.print(desc);
    }

    @Override
    public void println(String desc) {
        System.out.println(desc);
    }

    @Override
    public void close() {
        render.close();
    }

    @Override
    public void render(Drawer drawer) {
        render.render(drawer);
        String value = render.getString();
        log.debug("New Image: {} {}", System.lineSeparator(), value);
        System.out.println(value);

    }
}
