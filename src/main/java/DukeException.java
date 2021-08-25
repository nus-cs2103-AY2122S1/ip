public class DukeException extends Exception{
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public class InvalidInputException extends DukeException {
        public InvalidInputException(String errorMessage) {
            super(errorMessage);
        }
    }
}
