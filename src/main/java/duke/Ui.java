package duke;

import duke.task.DukeList;

import java.util.Scanner;

public class Ui {

    private final Parser PARSER;

    private static final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DUKE_GREETING = "\nHello I'm duke.Duke!\n"
            + "What can I do for you?\n";
    private static final String DUKE_EXIT = "Bye. Hope to see you again soon!";

    public Ui(DukeList list, Storage storage) {
        this.PARSER = new Parser(list, storage);
    }

    public void run() {
        System.out.println("Hello from\n" + DUKE_LOGO + DUKE_GREETING);

        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();

        while (!input.equals("bye")) {
            this.PARSER.parse(input);

            input = scan.nextLine();
        }

        System.out.println(DUKE_EXIT);
    }
}
