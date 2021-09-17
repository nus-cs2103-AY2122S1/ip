package duke;

import java.util.Scanner;

public class Ui {
    public static final String UI_ERROR_HEADING = "Oops, Error detected:";

    private final Scanner sc;


    public Ui() {
        sc = new Scanner(System.in);
    }

    @Override
    public String toString() {
        return "Yiyang-bot's UI";
    }

    /**
     * Reads a new command from console.
     * @return the new command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays greetings when the program first runs.
     */
    public String displayGreetings() {
        return "\tHello this is Yiyang-bot :D"
                + "\n" + "\tWhat can I do for you?";
    }

    /**
     * Displays bye when the program ends.
     */
    public String displayBye() {
        return "\tBye. Hope to see you again.";
    }

    /**
     * Prints a line divider.
     */
    public String showLine() {
        return "\t--------------------------------------------------";
    }

    /**
     * Displays error message.
     * @param errorMsg the error message to be displayed.
     */
    public String showError(String errorMsg) {
        return errorMsg;
    }
}
