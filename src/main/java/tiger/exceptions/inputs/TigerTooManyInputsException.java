package tiger.exceptions.inputs;

public class TigerTooManyInputsException extends TigerInvalidInputException {
    public TigerTooManyInputsException(String e) {
        super("Too many arguments specified!");
    }
}
