package duke.exception;

import java.io.IOException;

/** Exception to be thrown when a file is unable to be created */
public class CreateFileException extends IOException {

    /**
     * Constructor for a CreateFileException
     */
    public CreateFileException() {
        super("Failed to create file, please restart the application.");
    }
}
