public abstract class DukeException extends Exception {
    public DukeException(String s) {
        super("☹ OOPS!!! " + s);
    }

    public static class EmptyDescriptionException extends DukeException {
        public EmptyDescriptionException() {
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
            super("Missing argument: " +  arg);
        }
    }
}
