package duke.exception;

public class NoNumberException extends DukeException {
    public NoNumberException(String msg) {
        super(msg);
    }

    @Override
    public void printError() {
        System.out.println("( ⚆ _ ⚆ ) OOPS!!! Please enter a task number!");
    }
}
