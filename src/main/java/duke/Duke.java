package duke;

import duke.views.cli.Cli;
import duke.views.gui.Launcher;
import duke.views.strategies.Parser;
import javafx.application.Application;

public class Duke {
    /**
     * Runs the CLI application.
     */
    public static void runCli() {
        Cli chatbot = new Cli(new Parser());
        chatbot.main();
    }

    public static void runGui(String[] args) {
        Application.launch(Launcher.class, args);
    }

    public static void main(String[] args) {
        runGui(args);
    }
}
