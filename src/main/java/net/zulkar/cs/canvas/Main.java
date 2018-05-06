package net.zulkar.cs.canvas;

import net.zulkar.cs.canvas.command.ConsoleCommandSupplier;
import net.zulkar.cs.canvas.parser.Parser;
import net.zulkar.cs.canvas.parser.app.CreateCanvasParser;
import net.zulkar.cs.canvas.parser.app.QuitParser;
import net.zulkar.cs.canvas.parser.draw.FillParser;
import net.zulkar.cs.canvas.parser.draw.LineParser;
import net.zulkar.cs.canvas.parser.draw.RectangleParser;
import net.zulkar.cs.canvas.render.ConsoleRenderer;
import net.zulkar.cs.canvas.render.VT100Renderer;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            Options options = getOptions();

            CommandLineParser parser = new DefaultParser();
            try {
                CommandLine line = parser.parse(options, args);
                if (line.hasOption("h")) {
                    printHelp(options);
                } else {
                    runApplication(line);
                }
            } catch (ParseException exp) {
                printHelp(options);
            }

        } catch (Throwable e) {
            log.error(e);
        }

    }

    private static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("canvas-challenge", options);
    }

    private static void runApplication(CommandLine line) {
        ConsoleRenderer renderer = getRender(line);
        ConsoleCommandSupplier consoleCommandSupplier = new ConsoleCommandSupplier(renderer);
        Parser parser = createParser();
        ActionsProcessor actionsProcessor = new ActionsProcessor();
        actionsProcessor.init(consoleCommandSupplier, parser, renderer);
        actionsProcessor.run();
    }

    private static ConsoleRenderer getRender(CommandLine args) {

        if (args.hasOption("vt100")) {
            log.info("Chose VT100 compatible render, os name: {}", System.getProperty("os.name"));
            return new VT100Renderer();
        } else {
            if (!StringUtils.startsWithIgnoreCase(System.getProperty("os.name"), "win")) {
                System.out.println("Looks like your terminal may support VT100 commands. Use --vt100 parameter to launch in vt100 compatible mode");
            }
            log.info("Chose simple console render, os name: {}", System.getProperty("os.name"));
            return new ConsoleRenderer();
        }

    }

    private static Options getOptions() {
        Option vt100 = Option.builder("v")
                .longOpt("vt100")
                .desc("use vt100-compatible render")
                .build();
        Option help = Option.builder("h")
                .longOpt("help")
                .desc("print this help and exit")
                .build();
        return new Options()
                .addOption(vt100)
                .addOption(help);

    }

    private static Parser createParser() {
        return new Parser(Arrays.asList(
                new LineParser(),
                new FillParser(),
                new RectangleParser(),
                new QuitParser(),
                new CreateCanvasParser()
        ));
    }
}
