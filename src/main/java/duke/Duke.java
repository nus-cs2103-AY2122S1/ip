package duke;

import duke.views.cli.Cli;
import duke.views.cli.strategies.MultiType;
import duke.views.gui.Launcher;
import javafx.application.Application;

public class Duke {
    /**
     * Initial method to test that the main method can run properly.
     */
    public static void test() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Runs the CLI application.
     */
    public static void runCli() {
        Cli chatbot = new Cli(new MultiType());
        chatbot.main();
    }

    public static void runGui(String[] args) {
        Application.launch(Launcher.class, args);
    }

    public static void main(String[] args) {
        runGui(args);
    }
}
