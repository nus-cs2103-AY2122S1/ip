import java.util.InputMismatchException;

public class NyxException extends InputMismatchException {
    public NyxException(String message) {
        super("\t☹ OOPS!!! " + message);
    }
}
