package duke.util;

import java.util.Scanner;

/**
 * Represents how Duke deals with interactions with the user.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class Ui {

    /** The command that Duke read from the user. */
    private String command;

    /**
     * Displays a welcome message everytime Duke is activated.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("-----------------------------------------");
        System.out.println(" Hello! I am Duke");
        System.out.println(" What can I do for you?");
        System.out.println("-----------------------------------------");
        System.out.println();
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays an error message to the user when there is an error loading the user file.
     */
    public void showLoadingError() {
        System.out.println("Something went wrong! Seems like I'm unable to load your file!");
    }

    /**
     * Displays an error message to the user when there is an error saving to the user file.
     */
    public void showSavingError() {
        System.out.println("Something went wrong! Seems like I'm unable to save to your file!");
    }

    /**
     * Returns the command read from the user through a scanner.
     *
     * @return A string representing the command read from the user.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        this.command = sc.nextLine();
        return this.command;
    }

    /**
     * Returns the command from the user as a String
     *
     * @return A string representing the command read from the user.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Displays an exit message before Duke exits.
     */
    public void showExit() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    /**
     * Displays a top line separator.
     */
    public void showTopLine() {
        System.out.println("    -----------------------------------------");
    }

    /**
     * Displays a bottom line separator.
     */
    public void showBottomLine() {
        System.out.println("    -----------------------------------------");
        System.out.println();
    }
}
