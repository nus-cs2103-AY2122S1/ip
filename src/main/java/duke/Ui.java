package duke;

/**
 *  A class that encapsulates the interactions with the user
 */
public class Ui {
    /**
     * Shows the welcome message when first executing Duke.
     */
    public void showWelcomeMessage() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you\n");
    }

    /**
     * Shows the goodbye message when finishing executing Duke.
     */
    public void showByeMessage() {
        System.out.println("\tBye bye!!. Hope to see you again soon!!");
    }

    /**
     * Shows the error message when encountering a DukeException.
     */
    public void showErrorMessage(DukeException e) {
        String red = "\u001B[91m";
        String def = "\u001B[39m";
        String border = "******************************";
        System.out.println(red + border + border + "\n\n"
                + e.toString() + "\n\n" + border + border
                + def + "\n");
    }

    public void showBreakLine() {
        String breakLine = "------------------------------";
        System.out.println(breakLine + breakLine);
    }

    public void showReply(String reply) {
        showBreakLine();
        System.out.println("\n" + reply);
        showBreakLine();
    }
}
