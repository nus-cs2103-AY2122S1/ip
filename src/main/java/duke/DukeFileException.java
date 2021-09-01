package duke;

import java.nio.file.NoSuchFileException;

// TODO: used for loading
public class DukeFileException extends NoSuchFileException {
    public DukeFileException(String file) {
        super(file);
    }

    @Override
    public String toString() {
        return "@OOPS!!! " + super.toString();
    }
}
