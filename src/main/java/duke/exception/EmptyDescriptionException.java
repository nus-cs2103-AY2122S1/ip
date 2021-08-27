package duke.exception;


public class EmptyDescriptionException extends DukeException {
    private String task;
    public EmptyDescriptionException(String msg, String task) {
        super(msg);
        this.task = task;
    }

    @Override
    public void printError() {
        System.out.printf("( ⚆ _ ⚆ ) OOPS!!! The description of a %s cannot be empty.\n", task);
    }
}
