package net.zulkar.cs.canvas.render;

import net.zulkar.cs.canvas.draw.Drawer;

public interface Renderer {

    void print(String desc);

    void println(String desc);

    void close();

    void render(Drawer drawer);
}
