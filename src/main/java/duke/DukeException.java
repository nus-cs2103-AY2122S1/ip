package duke;

public abstract class DukeException extends Exception {
    public DukeException(String s) {
        super("OOPS!!! " + s);
    }

    public static class EmptyTaskDescriptionException extends DukeException {
        public EmptyTaskDescriptionException() {
            super("The description of a task cannot be empty.");
        }
    }
    public static class UnknownInputException extends DukeException {
        public UnknownInputException() {
            super("I'm sorry, but I don't know what that means :-(");
        }
    }
    public static class MissingArgumentException extends DukeException {
        public MissingArgumentException(String arg) {
            super("Missing argument(s): " +  arg);
        }
    }

    public static class InvalidReprException extends DukeException {
        public InvalidReprException(String s) {
            super("Invalid repr: " + s);
        }
    }

    public static class DateException extends DukeException {
        public DateException() {
            super("I cannot snooze this event! :(");
        }
    }
}
