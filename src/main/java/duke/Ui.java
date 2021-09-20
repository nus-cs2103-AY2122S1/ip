package duke;

import java.util.Scanner;

/**
 * Represents the class that handles all the interactions with the user.
 */
public class Ui {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner scanner;


    /**
     * Constructs the handler.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can I do for you?");
    }

    /**
     * Displays the error message.
     *
     * @param errorMessage The given error message.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Returns the given command from the user.
     *
     * @return The most recent command from the user.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Displays loading error message.
     */
    public void showLoadingError() {
        System.out.println("Oh no, looks like you don't have a existing list! "
                + "Lets help you create a new one!");
    }
}
