package duke;

/**
 * Exceptions for the Duke app.
 */
public class DukeException extends Exception {

    public enum Errors {
        FILE_NOT_FOUND ("Saved File not found"),
        INVALID_COMMAND ("what you mean? Do: `help` to view all commands."),
        MISSING_DESCRIPTION ("Command is missing a description."),
        MISSING_DATE ("Command is missing a date."),
        INVALID_DATE ("The date is invalid.  Do `dates` command to see all possible dates."),
        INVALID_TIME ("The time is invalid.  Do `dates` command to see all possible time."),
        INVALID_ARGUMENT ("The argument is invalid."),
        WRONG_ARGUMENT_TYPE ("Invalid argument type."),
        TASK_NOT_FOUND ("No task found."),
        SAVE_FAIL ("Failed to save current state");

        private final String description;

        Errors(String description) {
            this.description = description;
        }

        /**
         * Returns the string representation of an Error
         * @return the string representation of an Error
         */
        @Override
        public String toString() {
            return description;
        }
    }

    DukeException(String message) {
        super(message);
    }

}
