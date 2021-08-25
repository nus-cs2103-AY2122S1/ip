package duke.exceptions;

public class RepeatedDoneException extends DukeException{

    public RepeatedDoneException() {
        super("The duke.task has already been completed");
    }

}
