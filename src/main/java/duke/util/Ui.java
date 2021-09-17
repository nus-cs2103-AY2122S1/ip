package duke.util;

import java.util.Scanner;

/**
 * Represents the user interface of the Duke program.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a user interface for the Duke program.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Greets users.
     */
    public void showWelcome() {
        String welcome = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(welcome);
    }

    /**
     * Reads commands inputted by users.
     *
     * @return the line of command inputted by users.
     */
    public String readCommand() {
        String nextLine = scanner.nextLine();
        while (nextLine.equals("")) {
            nextLine = scanner.nextLine();
        }
        assert !nextLine.equals("") : "Next line should not be an empty string";
        return nextLine;
    }

    /**
     * Prints error message onto the console.
     *
     * @param errorMsg Error message to be printed onto the console.
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Prints a divider with no new line.
     */
    public void showOpeningLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a divider with a new line.
     */
    public void showClosingLine() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints the response after users inputted a command onto the console.
     *
     * @param response Response to be printed onto the console after users inputted a command.
     */
    public void showResponse(String response) {
        System.out.println(response);
    }

    /**
     * Closes the scanner.
     */
    public void cleanup() {
        scanner.close();
    }
}
