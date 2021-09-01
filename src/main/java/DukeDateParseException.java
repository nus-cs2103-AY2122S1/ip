import java.text.ParseException;

// TODO: used for loading
public class DukeDateParseException extends ParseException {
    public DukeDateParseException(String s, int errorOffset) {
        super(s, errorOffset);
    }

    public DukeDateParseException(String s) {
        // parsing tasks do not need an offset
        super(s, 0);
    }

    public DukeDateParseException(ParseException e) {
        super(e.getMessage(), e.getErrorOffset());
    }

    @Override
    public String toString() {
        return "@OOPS!!! Date invalid at " + this.getErrorOffset() + ", " + super.getMessage();
    }
}
