package ashy.exceptions;

public class MultipleKeywordException extends AshyException {
    public MultipleKeywordException() {
        super("Oh no! Please enter only a single keyword! ☹");
    }
}
