package duke.storage;

import java.io.IOException;

/**
 * Signals that the file to be read is in an unknown format.
 *
 * @author cai
 */
public class FileFormatException extends IOException {
    private static final String UNKNOWN_FILE_FORMAT_MESSAGE = "Unknown file format: %s";

    FileFormatException(String message) {
        super(String.format(UNKNOWN_FILE_FORMAT_MESSAGE, message));
    }

    FileFormatException(String message, Throwable cause) {
        super(String.format(UNKNOWN_FILE_FORMAT_MESSAGE, message), cause);
    }
}
