package Duke.Storage;

import java.io.IOException;

public class FileFormatException extends IOException {
    private static final String UNKNOWN_FILE_FORMAT_MESSAGE = "Unknown file format: %s";

    FileFormatException(String message) {
        super(String.format(UNKNOWN_FILE_FORMAT_MESSAGE, message));
    }

    FileFormatException(String message, Throwable cause) {
        super(String.format(UNKNOWN_FILE_FORMAT_MESSAGE, message), cause);
    }
}
