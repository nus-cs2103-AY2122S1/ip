public class DukeException extends Exception{
    protected String message;

    /** Constructs a new duke exception with the specified detail message.
     *
     * @param   message   the detail error message. 
     */
    public DukeException(String message) {
        super(message);
    }
}
