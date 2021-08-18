public class NoDescriptionException extends DukeException {
    /**
     * A constructor for this NoDescriptionException.
     *
     * @param task the task that was attempting to be added which
     *             resulted in this exception.
     */
    public NoDescriptionException(String task) {
        super("A description of the " + task + " is required!");
    }
}
