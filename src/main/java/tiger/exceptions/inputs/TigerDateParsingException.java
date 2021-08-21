package tiger.exceptions.inputs;

public class TigerDateParsingException extends TigerInvalidInputException {
    public TigerDateParsingException(String e) {
        super("Please ensure you key in dates in the input specified.");
        System.out.println(e);
    }
}
