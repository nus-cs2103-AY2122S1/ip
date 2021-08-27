package duke.exception;
public class InvalidEntryException extends DukeException {
    public InvalidEntryException(String msg) {
        super(msg);
    }

    @Override
    public void printError() {
        System.out.println("( ⚆ _ ⚆ ) OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
