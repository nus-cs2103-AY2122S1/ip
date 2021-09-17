package duke;

import java.text.ParseException;

/**
 * Exception that is thrown when there is a problem with date format.
 */
public class DukeDateParseException extends ParseException {

    public DukeDateParseException(ParseException e) {
        super(e.getMessage(), e.getErrorOffset());
    }

    @Override
    public String toString() {
        return "@OOPS!!! Date invalid at " + this.getErrorOffset() + ", " + super.getMessage();
    }
}
