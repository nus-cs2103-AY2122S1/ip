public class DukeException extends RuntimeException {
    String errorMessage;

    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }

    static class MissingTaskException extends DukeException {
        public MissingTaskException() {
            super("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static class InvalidInputException extends DukeException {
        public InvalidInputException() {
            super("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
