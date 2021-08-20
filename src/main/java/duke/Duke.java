package duke;

import duke.views.cli.CLI;
import duke.views.cli.strategies.*;

public class Duke {
    public static void test() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void run() {
        CLI chatbot = new CLI(new MultiType());
        chatbot.main();
    }

    public static void main(String[] args) {
        run();
    }
}
