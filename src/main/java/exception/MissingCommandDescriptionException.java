package exception;

/**
 * Encapsulates the exception thrown when a user inputs an action with an empty description,
 * when the action requires a description.
 */
public class MissingCommandDescriptionException extends DukeException {
    /**
     * Constructor to instantiate a `exception.MissingActionDescriptionException`
     *
     * @param actionType is the type of the action
     */
    public MissingCommandDescriptionException(String actionType) {
        super(String.format(
           "The description of a %s command cannot be empty",
           actionType
        ));
    }
}
