package duke;

/**
 * This class encapsulates exceptions which the Duke program could throw/
 */
public class DukeException extends Exception {

    /**
     * This exception occurs when an input is invalid
     * (e.g. passes a String instead of an int).
     */
    public static class InvalidInputException extends Exception {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    /**
     * This exception occurs when the user attempts to create a
     * deadline/event without specifying the time.
     */
    public static class NoTimeSpecifiedException extends Exception {
        public NoTimeSpecifiedException(String message) {
            super(message);
        }
    }

    /**
     * This exception occurs when the user attempts to
     * add in a task with no name.
     */
    public static class NoNameException extends Exception {
        public NoNameException(String message) {
            super(message);
        }
    }

    /**
     * This exception occurs when the user attempts to
     * snooze a task which does not have a dateTime
     * (i.e. a to-do type task)
     */
    public static class UnsnoozeableTaskException extends Exception {
        public UnsnoozeableTaskException(String message) {
            super(message);
        }
    }

}
