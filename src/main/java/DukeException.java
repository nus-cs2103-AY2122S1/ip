public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public void displayError() {
        Duke.ui.resetMessage();
        Duke.ui.addMessage("Oops! Something went wrong :(\n", TextColor.DEFAULT);
        Duke.ui.addMessage(getMessage(), TextColor.RED);
    }
}
