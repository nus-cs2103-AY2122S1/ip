package tiger.exceptions;

public class TigerInvalidArgumentException extends TigerException {
    public TigerInvalidArgumentException(String property, String command) {
        super(String.format("%s is not a valid argument for the %s command.", property, command));
    }
}
