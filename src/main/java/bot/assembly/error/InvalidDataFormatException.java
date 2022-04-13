package bot.assembly.error;

/**
 * An exception that will be thrown when the data loaded from data.txt is:
 * 1) in the wrong format
 * 2) corrupted
 */
public class InvalidDataFormatException extends ArrayIndexOutOfBoundsException {

    /**
     * Constructor
     * @param msg to reply to the user
     */
    public InvalidDataFormatException(String msg) {
        super(msg);
    }
}
