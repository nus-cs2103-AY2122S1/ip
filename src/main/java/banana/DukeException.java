package banana;


/**
 * The DukeException class
 * throws specialised Duke exceptions.
 *
 * @author: Ravi Ananya
 **/
class DukeException extends Exception {

    private String errorMessage;

    /**
     * Constructor for DukeException.
     *
     * @param errorMessage user input.
     */
    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the error message.
     *
     * @return the error message.
     */
    @Override
    public String getMessage() {
        return errorMessage;
    }

}
