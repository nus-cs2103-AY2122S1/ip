package bot.assembly.error;

import java.io.IOException;

/**
 * An exception that will be thrown when the data.txt file is having:
 * 1) IO issue
 */
public class InvalidFileException extends IOException {

    /**
     * Constructor
     * @param msg to reply to the user
     */
    public InvalidFileException(String msg) {
        super(msg);
    }
}
