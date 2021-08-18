package duke.exceptions;

public class UnknownTaskTypeException extends DukeException {
    public UnknownTaskTypeException(String content) {
        super("No such task type \"" + content + "\"");
    }
}
