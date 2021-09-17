package duke;

import java.nio.file.NoSuchFileException;

/**
 * @DukeFileException is an exception that is thrown when there are errors with a file existing.
 */
public class DukeFileException extends NoSuchFileException {
    public DukeFileException(String file) {
        super(file);
    }

    @Override
    public String toString() {
        return "@OOPS!!! " + super.toString();
    }
}
