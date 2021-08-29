package duke;

import java.util.Scanner;

import duke.task.DukeList;


/**
 * Encapsulates the user interaction from duke.
 */
public class Ui {

    /** Duke's logo. */
    private static final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    /** Duke's greeting. */
    private static final String DUKE_GREETING = "\nHello I'm Duke!\n"
            + "What can I do for you?\n";

    /** Duke's farewells. */
    private static final String DUKE_EXIT = "Bye. Hope to see you again soon!";

    /** Parser of user input. */
    private final Parser parser;

    /**
     * Constructs a Ui object.
     *
     * @param list Duke's list.
     * @param storage Duke's storage.
     */
    public Ui(DukeList list, Storage storage) {
        this.parser = new Parser(list, storage);
    }

    /**
     * Starts Duke's interactions with the user.
     */
    public void run() {
        System.out.println("Hello from\n" + DUKE_LOGO + DUKE_GREETING);

        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();

        while (!input.equals("bye")) {
            this.parser.parse(input);

            input = scan.nextLine();
        }

        System.out.println(DUKE_EXIT);
    }
}
