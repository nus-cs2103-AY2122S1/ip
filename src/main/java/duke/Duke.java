package duke;

import duke.views.cli.CLI;
import duke.views.cli.strategies.*;

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
    public static void run() {
        CLI chatbot = new CLI(new MultiType());
        chatbot.main();
    }

    public static void main(String[] args) {
        run();
    }
}
