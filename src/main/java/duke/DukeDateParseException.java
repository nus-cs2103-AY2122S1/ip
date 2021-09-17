package duke;

import java.text.ParseException;


/**
 * @DukeDateParseException is an exception that is thrown when date format passed into Duke commands are incorrect.
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
