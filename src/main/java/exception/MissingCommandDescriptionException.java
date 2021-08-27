package exception;

/**
 * Encapsulates an exception when a user inputs a command with an empty description,
 * but the command requires a description.
 */
public class MissingCommandDescriptionException extends DukeException {
    /**
     * Instantiates an exception when a user inputs a command with an empty description.
     *
     * @param commandType Command type.
     */
    public MissingCommandDescriptionException(String commandType) {
        super(String.format(
                "The description of a %s command cannot be empty",
                commandType
        ));
    }
}
