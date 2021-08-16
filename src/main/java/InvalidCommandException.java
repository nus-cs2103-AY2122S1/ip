public class InvalidCommandException extends RuntimeException {
    InvalidCommandException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of InvalidCommandException
     *
     * @return The string representation of InvalidCommandException
     */
    @Override
    public String toString() {
        return "InvalidCommandException: " + getMessage();
    }
}
