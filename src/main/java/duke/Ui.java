package duke;

import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Howdy! The name's\n" + logo + "\nWhat can I do for ya?");
    }

    public void showFarewell() {
        System.out.println("Seeya!");
    }

    /**
     * Prints a given String to console.
     * @param message message to be printed to console.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Shows a given exception error message to user.
     * @param errorMessage Error message to show to user.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

}
