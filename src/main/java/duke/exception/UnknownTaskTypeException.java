package duke.exception;

/** Used when users type nonsense as Task. */
public class UnknownTaskTypeException extends DukeException {
    public UnknownTaskTypeException(String content) {
        super("No such task type \"" + content + "\"");
    }
}
