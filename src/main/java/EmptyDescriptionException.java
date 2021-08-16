public class EmptyDescriptionException extends RuntimeException {
    EmptyDescriptionException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of EmptyDescriptionException
     *
     * @return The string representation of EmptyDescriptionException
     */
    @Override
    public String toString() {
        return "EmptyDescriptionException: " + getMessage();
    }
}
