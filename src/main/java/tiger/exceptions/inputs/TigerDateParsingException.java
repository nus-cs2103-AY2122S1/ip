package tiger.exceptions.inputs;

import tiger.messages.Messages;

public class TigerDateParsingException extends TigerInvalidInputException {
    public TigerDateParsingException(String e) {
        super(Messages.EXCEPTION_INPUT_DATE_PARSING.getMessage());
    }
}
