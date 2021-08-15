package main.java;

import views.cli.CLI;
import views.cli.strategies.*;

public class Duke {
    public static void test() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void run() {
        CLI chatbot = new CLI(new SimpleListStorage());
        chatbot.listen();
    }

    public static void main(String[] args) {
        run();
    }
}
