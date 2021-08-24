package duke.dukeexception;

import java.io.FileNotFoundException;
import java.io.IOException;

public class NoListException extends DukeException {

    public NoListException(Exception e) {
        super(e);
    }
}
