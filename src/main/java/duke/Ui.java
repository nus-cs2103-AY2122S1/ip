package duke;

import java.util.Scanner;

/**
 * Ui class deals with loading tasks from the file and saving tasks in the file
 */
public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prompts user input and returns it.
     *
     * @return Command entered by user.
     */
    public String readCommand() {
        // TODO: close() scanner
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        return next;
    }

    /**
     * Prints loading error.
     */
    public void showLoadingError() {
        System.err.println("☹ OOPS!!! Seems like your data is corrupted. " +
                "Please make sure you data file has the correct format.");
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        this.showLine();
        System.out.println(LOGO);
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
        this.showLine();
    }

    /**
     * Prints good bye message.
     */
    public void showGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints divider.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints error message specified.
     *
     * @param message Error message to be printed.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
