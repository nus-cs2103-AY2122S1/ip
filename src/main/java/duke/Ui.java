package duke;

/**
 *  A class that encapsulates the interactions with the user.
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
        String bye = "\tBye bye!!. Hope to see you again soon!!";
        showBreakLine();
        System.out.println(bye);
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

    /**
     * Shows the BreakLine that is shown at the start and end of each Duke's reply.
     */
    public void showBreakLine() {
        String breakLine = "------------------------------";
        System.out.println(breakLine + breakLine + "\n");
    }

    /**
     * Shows the formatted reply from Duke after receiving a input from the user.
     *
     * @param reply The String representation of the reply provided from Duke
     */
    public void showReply(String reply) {
        showBreakLine();
        System.out.println(reply);
        showBreakLine();
    }
}
