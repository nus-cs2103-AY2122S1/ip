public class InvalidValueException extends RuntimeException {
    InvalidValueException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of InvalidValueException
     *
     * @return The string representation of InvalidValueException
     */
    @Override
    public String toString() {
        return "InvalidValueException: " + getMessage();
    }
}
