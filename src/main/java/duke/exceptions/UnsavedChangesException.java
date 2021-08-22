package duke.exceptions;

public class UnsavedChangesException extends DukeException {
    public UnsavedChangesException() {
        super("Changes not saved to file... please check if everything is ok");
    }
}
