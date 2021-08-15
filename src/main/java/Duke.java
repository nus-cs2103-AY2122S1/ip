package main.java;

import modules.cli.CLI;
import modules.cli.strategies.Echo;

public class Duke {
    public static void test() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void run() {
        CLI chatbot = new CLI(new Echo());
        chatbot.listen();
    }

    public static void main(String[] args) {
        run();
    }
}
