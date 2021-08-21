package duke;

import java.util.Scanner;

public class Ui {
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
    public void displayGreetings() {
        System.out.println("\tHello this is Yiyang-bot :D");
        System.out.println("\tWhat can I do for you?");
    }

    /**
     * Displays bye when the program ends.
     */
    public void displayBye() {
        System.out.println("\tBye. Hope to see you again.");
    }

    /**
     * Prints a line divider.
     */
    public void showLine() {
        System.out.println("\t--------------------------------------------------");
    }

    /**
     * Displays error message.
     * @param errorMsg the error message to be displayed.
     */
    public void showError(String errorMsg) {
        System.err.println(errorMsg);
    }
}
