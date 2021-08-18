public class DukeException extends Exception {
    // causes text to print red
    private static final String ANSI_RED = "\u001B[31m";
    // resets color formatting on text
    private static final String ANSI_RESET = "\u001B[0m";

    public DukeException(String message) {
        super(message);
    }

    public void displayError() {
        Duke.printMessage(
                "Oops! Something went wrong :(\n" +
                ANSI_RED + getMessage() + ANSI_RESET);
    }
}
