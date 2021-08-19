import java.util.NoSuchElementException;

public class DukeTaskException extends NoSuchElementException {
    DukeTaskException(String error) {
        super(error);
    }

    @Override
    public String getMessage() {
        return "@OPPS!!! " + super.getMessage();
    }
}
