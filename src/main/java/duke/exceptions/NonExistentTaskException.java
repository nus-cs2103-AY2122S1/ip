package duke.exceptions;

public class NonExistentTaskException extends DukeException{

    public NonExistentTaskException() {
        super("The duke.task does not exist in the list");
    }

}
