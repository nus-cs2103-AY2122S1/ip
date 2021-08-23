package tiger.exceptions.inputs;

import tiger.constants.Messages;

public class TigerTooManyInputsException extends TigerInvalidInputException {
    public TigerTooManyInputsException(String e) {
        super(Messages.EXCEPTION_INPUT_TOO_MANY_ARGUMENTS.getMessage());
    }
}
