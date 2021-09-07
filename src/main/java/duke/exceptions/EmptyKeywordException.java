package duke.exceptions;

public class EmptyKeywordException extends DukeException {
    public EmptyKeywordException() {
        super("Please enter a keyword to look for! ☹");
    }
}
