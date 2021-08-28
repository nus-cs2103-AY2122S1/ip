package duke;

/**
 * Represents the exceptions unique to Duke chat bot.
 *
 * @author Adam Ho
 */
public class DukeException extends Exception {
    private String errorMessage;
    /**
     * Creates a duke exception with the specified error message.
     * @param errorMessage The error message to display when the error is encountered.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "DukeException{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }

    /**
     * Represents an exception when no description is provided in the user input.
     */
    public static class MissingDescriptionException extends DukeException {
        /**
         * Creates an exception with the following error message.
         */
        public MissingDescriptionException() {
            super("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Represents an exception when the user input does not match the command enums.
     */
    public static class InvalidInputException extends DukeException {
        /**
         * Creates an exception with the following error message.
         */
        public InvalidInputException() {
            super("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Represents an exception when the user tries to add or delete a non-existing task.
     */
    public static class MissingTaskException extends DukeException {
        /**
         * Creates an exception with the following error message.
         */
        public MissingTaskException() {
            super("Huh? You don't have that task option!");
        }
    }
}
