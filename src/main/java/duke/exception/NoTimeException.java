package duke.exception;

public class NoTimeException extends DukeException {
    public NoTimeException(String string){
        super("OOPS! The time of " + string +" cannot be empty.");
    }
}
