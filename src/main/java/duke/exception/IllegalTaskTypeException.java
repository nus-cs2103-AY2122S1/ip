package duke.exception;

/**
 * Throws exception when task type does not exist.
 */
public class IllegalTaskTypeException extends DukeException {

    public IllegalTaskTypeException(String taskTypeStr) {
        super(("Meow? There is no task type " + taskTypeStr + "!"));
    }

}
