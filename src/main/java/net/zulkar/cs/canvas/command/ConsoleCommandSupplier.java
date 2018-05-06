package net.zulkar.cs.canvas.command;

import net.zulkar.cs.canvas.render.ConsoleRenderer;

import java.util.Optional;
import java.util.Scanner;

public class ConsoleCommandSupplier implements CommandSupplier {

    public static final String PROMPT = "enter command:";
    private final ConsoleRenderer renderer;
    private final Scanner scanner;


    public ConsoleCommandSupplier(ConsoleRenderer renderer) {
        scanner = new Scanner(System.in);
        this.renderer = renderer;
    }

    @Override
    public Optional<String> getNextCommand() {
        renderer.println("");
        renderer.print(PROMPT);
        Optional<String> readCommand = Optional.ofNullable(scanner.nextLine());
        return readCommand;
    }
}
