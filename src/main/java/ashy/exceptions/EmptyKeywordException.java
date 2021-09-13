package ashy.exceptions;

public class EmptyKeywordException extends AshyException {
    public EmptyKeywordException() {
        super("Please enter a keyword to look for! ☹");
    }
}
