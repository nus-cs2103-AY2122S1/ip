package duke.exception;

public class NoDescriptionAndTimeException extends DukeException {
    public NoDescriptionAndTimeException(String string){
        super("OOPS! The description & time of " + string +" cannot be empty.");
    }
}
