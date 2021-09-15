package duke.exception;

import java.io.IOException;

public class CreateFileException extends IOException {
    public CreateFileException() {
        super("Failed to create file, please restart the application.");
    }
}
