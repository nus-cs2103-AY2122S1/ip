package tiger.exceptions.inputs;

import tiger.exceptions.TigerException;

public class TigerInvalidInputException extends TigerException {

    public TigerInvalidInputException(String error) {
        super(error);
    }
}
