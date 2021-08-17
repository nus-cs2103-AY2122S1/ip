public class NoActionException extends Exception{
    public NoActionException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return String.format("No Action Provided: %s", super.getMessage());
    }
}
