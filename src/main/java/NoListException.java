import java.io.FileNotFoundException;
import java.io.IOException;

class NoListException extends DukeException {

    public NoListException(Exception e) {
        super(e);
    }
}
