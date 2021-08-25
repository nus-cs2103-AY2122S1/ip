package duke.exceptions;

public class MissingKeywordException extends DukeException{
    public MissingKeywordException() { }

    @Override
    public String toString() {
        return "OOPS!!! To find a task, the keyword must be stated.";
    }
}
