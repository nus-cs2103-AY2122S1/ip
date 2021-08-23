package duke;

import duke.task.DukeList;

import java.util.Scanner;

/**
 * Encapsulates the user interaction from duke.
 */
public class Ui {

    /** Parser of user input. */
    private Parser parser;

    /** Duke's logo. */
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /** Duke's greeting. */
    private static String greeting = "\nHello I'm Duke!\n"
            + "What can I do for you?\n";

    /** Duke's farewells. */
    private static String exit = "Bye. Hope to see you again soon!";

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
        System.out.println("Hello from\n" + logo + greeting);

        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();

        while(!input.equals("bye")) {
            this.parser.parse(input);

            input = scan.nextLine();
        }

        System.out.println(exit);
    }
}
