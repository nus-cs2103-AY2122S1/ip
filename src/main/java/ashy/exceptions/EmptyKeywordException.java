package ashy.exceptions;

public class EmptyKeywordException extends AshyException {
    public EmptyKeywordException() {
        super("Oh no! Please enter a keyword to look for! ☹");
    }
}
