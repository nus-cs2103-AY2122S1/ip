public class DukeException extends Exception {
    /**
     * Constructor of the class `DukeException`.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        super("____________________________________________________________\n" +
                message +
                "\n____________________________________________________________\n");
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
