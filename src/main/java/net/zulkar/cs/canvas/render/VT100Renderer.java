package net.zulkar.cs.canvas.render;

import net.zulkar.cs.canvas.draw.Drawer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VT100Renderer extends ConsoleRenderer {
    private static final Logger log = LogManager.getLogger(VT100Renderer.class);

    private final StringStandardRender render;

    public VT100Renderer() {
        render = new StringStandardRender();
    }

    @Override
    public void print(String desc) {
        System.out.print("\033[31;1;4m" + desc + "\033[0m");
    }

    @Override
    public void println(String desc) {
        System.out.println("\033[31;1;4m" + desc + "\033[0m");
    }

    @Override
    public void close() {
        render.close();
    }

    @Override
    public void render(Drawer drawer) {
        System.out.print("\033[2J");
        render.render(drawer);
        String value = render.getString();
        log.debug("New Image: {} {}", System.lineSeparator(), value);
        System.out.println(value);
    }
}
