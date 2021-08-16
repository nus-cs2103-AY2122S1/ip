/**
 * Encapsulates the exception thrown when a user inputs an action with an empty description,
 * when the action requires a description.
 */
public class MissingActionDescriptionException extends DukeException {
    /**
     * Constructor to instantiate a `MissingActionDescriptionException`
     *
     * @param actionType is the type of the action
     */
    public MissingActionDescriptionException(String actionType) {
        super(String.format(
           "The description of a %s command cannot be empty",
           actionType
        ));
    }
}
