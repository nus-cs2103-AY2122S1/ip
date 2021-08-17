package exceptions;

public class NoSuchCommandException extends Exception {

    public NoSuchCommandException(String exceptionMsg) {
        super(exceptionMsg);
    }
}
