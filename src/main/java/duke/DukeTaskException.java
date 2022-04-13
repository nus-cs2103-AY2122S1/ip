package duke;

import java.util.NoSuchElementException;

/**
 * Exception that is thrown when there is no valid task found for a chatbot command.
 */
public class DukeTaskException extends NoSuchElementException {
    DukeTaskException(String error) {
        super(error);
    }

    @Override
    public String getMessage() {
        return "@OPPS!!! " + super.getMessage();
    }
}
