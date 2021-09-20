package captain;

/**
 * Represents the exceptions unique to Duke chatbot.
 *
 * @author Adam Ho
 */
public class DukeException extends Exception {
    /**
     * Creates a duke exception with the specified error message.
     * @param errorMessage The error message to display when the error is encountered.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
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
            super("OOPS!!! Please type a command that I can understand!");
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

    public static class InvalidTaskIndexException extends DukeException {
        public InvalidTaskIndexException() {
            super("Are you sure you've typed in a valid task index...?");
        }
    }

    public static class InvalidDateException extends DukeException {
        public InvalidDateException() {
            super("Please input the date in the following format: dd/mm/yyyy!");
        }
    }
}
