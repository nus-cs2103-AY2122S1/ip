package Exceptions;

public class InvalidTimeStampException extends DukeException {

    public InvalidTimeStampException(String errorMessage) {
        super(String.format(
                "This timestamp \"%s\" is invalid, Duke requires d/M/yyyy HHmm format",
                errorMessage
        ));
    }
}
