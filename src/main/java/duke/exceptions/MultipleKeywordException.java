package duke.exceptions;

public class MultipleKeywordException extends DukeException {
    public MultipleKeywordException() {
        super("Please enter only a single keyword! ☹");
    }
}
