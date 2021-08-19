import java.lang.Exception;

public class InvalidCommandException extends Exception{
    public InvalidCommandException(String exMsg) {
        super(exMsg);
    }
}