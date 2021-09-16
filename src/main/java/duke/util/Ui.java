package duke.util;

import java.util.Scanner;

/**
 * This class encapsulates the UI logic for the application.
 */
public class Ui {
    private static final String LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "-----------------------------------------------------------------------\n";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the next user inputted line.
     *
     * @return The user inputted line as a string.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints the message to the screen.
     *
     * @param msg The message to be printed.
     */
    public static void printMessage(String msg) {
        System.out.println(LINE + msg + LINE);
    }

    /**
     * Displays the welcome text when first entering the application.
     */
    public static void showWelcomeText() {
        Ui.printMessage(LOGO + "\nHello! I'm Duke :)\nWhat can I do for you?\n");
    }

    /**
     * Displays the ending text when exiting the application.
     */
    public static void showEndText() {
        Ui.printMessage("Bye. Hope to see you again soon!\n");
    }

    /**
     * Displays an loading error message when the application save file is missing or non-existent.
     */
    public static void showLoadingError() {
        Ui.printMessage("Seems like you do not have a previous save file. "
                + "I will create one for you once you input a task!\n");
    }
}
