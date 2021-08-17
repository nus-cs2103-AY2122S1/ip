public class InvalidInputException extends Exception{
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return String.format("Invalid Input: %s", super.getMessage());
    }
}
