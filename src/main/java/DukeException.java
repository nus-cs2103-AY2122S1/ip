public class DukeException extends Exception {

    public static class EmptyTaskException extends Exception {
        public EmptyTaskException(String message) {
            super(message);
        }
    }

    public static class InvalidInputException extends Exception {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    public static class NoTimeSpecifiedException extends Exception {
        public NoTimeSpecifiedException(String message) {
            super(message);
        }
    }

    public static class NoNameException extends Exception {
        public NoNameException(String message) {
            super(message);
        }
    }

}
