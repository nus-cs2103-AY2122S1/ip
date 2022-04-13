package tiger.exceptions.inputs;

public class TigerInvalidArgumentException extends TigerInvalidInputException {
    public TigerInvalidArgumentException(String property, String command) {
        super(String.format("%s is not a valid argument for the %s command.", property, command));
    }
}
