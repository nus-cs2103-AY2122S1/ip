package exception;

/**
 * Encapsulates an exception when a user inputs an action with an empty description,
 * but the action requires a description.
 */
public class MissingCommandDescriptionException extends DukeException {
    public MissingCommandDescriptionException(String actionType) {
        super(String.format(
           "The description of a %s command cannot be empty",
           actionType
        ));
    }
}
