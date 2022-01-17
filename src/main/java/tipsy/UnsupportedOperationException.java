package tipsy;

public class UnsupportedOperationException extends TipsyException {
    public UnsupportedOperationException() {
        super("No such operation supported");
    }
}
