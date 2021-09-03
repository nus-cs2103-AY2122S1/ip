package tiger.exceptions.inputs;

import tiger.constants.Messages;

public class TigerDateParsingException extends TigerInvalidInputException {
    public TigerDateParsingException() {
        super(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage());
    }
}
