package duke;

import java.util.Scanner;

/**
 * Encapsulates the user interaction from duke.
 */
public class Ui {

    /** Duke's logo. */
    public static final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /** Duke's greeting. */
    public static final String DUKE_GREETING = "\nHello I'm Duke!\n"
            + "What can I do for you?\n";

    /** Duke's farewells. */
    public static final String DUKE_EXIT = "Bye. Hope to see you again soon!";

    /** Parser of user input. */
    private final Parser parser;

    /**
     * Constructs a Ui object.
     *
     * @param parser Duke's parser of user input.

     */
    public Ui(Parser parser) {
        this.parser = parser;
    }

    /**
     * Starts Duke's interactions with the user.
     */
    public void run() {
        System.out.println("Hello from\n" + DUKE_LOGO + DUKE_GREETING);

        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();

        while (!input.equals("bye")) {
            System.out.println(parser.parse(input));

            input = scan.nextLine();
        }

        System.out.println(DUKE_EXIT);
    }
}
