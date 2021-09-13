package ashy.exceptions;

public class MultipleKeywordException extends AshyException {
    public MultipleKeywordException() {
        super("Please enter only a single keyword! ☹");
    }
}
