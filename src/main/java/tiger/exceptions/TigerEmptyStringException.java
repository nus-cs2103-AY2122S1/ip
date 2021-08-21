package tiger.exceptions;

public class TigerEmptyStringException extends TigerException {
    public TigerEmptyStringException(String property) {
        super(String.format("%s property cannot be empty.\nPlease ensure you key in the command in the format specified.", property));
    }
}
