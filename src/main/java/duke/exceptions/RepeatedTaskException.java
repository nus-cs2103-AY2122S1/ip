package duke.exceptions;

public class RepeatedTaskException extends DukeException{

    public RepeatedTaskException() {
        super("The above duke.task already exists in the list");
    }

}
