package duke;

public class Ui {
    private static final String BORDER = "---------------------------------------------------";
    private static final String GREETING = "Hello! I'm Duke, what can I do for you?";
    private static final String FAREWELL = "Bye. Hope to see you again soon!";

    private void printDuke(String str) {
        System.out.print(String.format("%s\n%s\n%s\n", BORDER, str, BORDER)
                .replaceAll("(?m)^", "\t"));
    }

    /**
     * Prints a Welcome message to the screen.
     *
     */
    public void showWelcome() {
        printDuke(GREETING);
    }

    /**
     * Prints a Farewell message to the screen.
     *
     */
    public void showFarewell() {
        printDuke(FAREWELL);
    }

    /**
     * Prints a message to the screen with border.
     *
     * @param message message to print to the screen
     */
    public void showMessage(String message) {
        printDuke(message);
    }
}
