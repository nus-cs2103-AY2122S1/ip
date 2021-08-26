package duke.data;

/**
 * Signals that an error has occurred when processing user input.
 * The user input could not be understood by the program or the
 * operation is not supported by the program.
 *
 * @author Zhi Bin
 * @version Duke Level 8
 */
public class UnknownCommandException extends DukeException{
    public UnknownCommandException(){
        super();
    }

    /**
     * Returns a formatted error message to signal user
     * that the input could not be read or the operation
     * they require is not supported by the program.
     *
     * @return The formatted error message.
     */
    @Override
    public String getMessage(){
        return super.formatMessage("I cannot understand what you want lah!!!");
    }
}
