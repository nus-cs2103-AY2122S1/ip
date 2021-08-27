package tiger.exceptions.inputs;

import tiger.constants.Messages;

public class TigerSemiColonException extends TigerInvalidInputException {
    public TigerSemiColonException(String e) {
        super(Messages.EXCEPTION_INPUT_SEMICOLON.getMessage());
    }
}
