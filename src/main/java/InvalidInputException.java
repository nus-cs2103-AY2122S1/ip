public class InvalidInputException extends UserInputError {
    InvalidInputException(String msg) {
        super(msg);
    }
    
    InvalidInputException() {
        super("Invalid input. Please try again.\nYou may use the command --help to know more!");
    }
}
