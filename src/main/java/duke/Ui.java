package duke;

import java.util.Scanner;

/**
 * The Ui class that deals with interactions with the user.
 */
public class Ui {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner sc;

    /**
     * Constructor for a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * A method that displays the greeting when the user starts the program.
     */
    public void greet() {
        System.out.println(LOGO);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * A method that displays the relevant error message to the user when exceptions are thrown.
     *
     * @param e The exception thrown by the program.
     */
    public void showError(DukeException e) {
        e.print();
    }

    /**
     * A method that displays the list of tasks to the user.
     *
     * @param list The task list to be displayed.
     */
    public void displayList(TaskList list) {
        System.out.println("Here's your list!");
        System.out.println(list.toString());
    }

    /**
     * A method that displays matching tasks with the correct statement after the user searches
     * for them.
     *
     * @param list The list of matching tasks to be displayed.
     */
    public void displayMatching(TaskList list) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(list.toString());
    }

    /**
     * A method to get input from the user.
     *
     * @return The input entered by the user.
     */
    public String getInput() {
        return sc.nextLine().strip();
    }

    /**
     * A method to stop user input.
     */
    public void stopInput() {
        System.out.println("Byebye");
        sc.close();
    }
}
