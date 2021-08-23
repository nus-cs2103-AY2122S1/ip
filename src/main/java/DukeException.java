public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public void displayError() {
        Duke.printMessage("Oops! Something went wrong :(\n" +
                StringFormatter.ANSI_RED + getMessage() + StringFormatter.ANSI_RESET);
    }
}
