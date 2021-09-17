package duke.ui.console;

import java.util.Scanner;

import duke.ui.Ui;

public class ConsoleUi extends Ui {
    private final Scanner in;

    public ConsoleUi(Scanner in) {
        this.in = in;
    }

    /**
     * Reads a command input by the user.
     */
    public String readCommand() {
        System.out.print("> ");
        return in.nextLine().trim();
    }

    /**
     * Prints a formatted message to the user.
     * @param string The message to print.
     */
    public void printMessage(String string) {
        System.out.print("------------------------------------------------\n" + string + "\n"
                + "------------------------------------------------\n\n");
    }
}
