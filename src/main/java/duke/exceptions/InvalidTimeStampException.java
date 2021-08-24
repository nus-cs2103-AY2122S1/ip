package duke.exceptions;

public class InvalidTimeStampException extends DukeException {

    public InvalidTimeStampException(String errorMessage) {
        super(String.format(
                "This timestamp \"%s\" is invalid, duke.Duke requires d/M/yyyy HHmm format",
                errorMessage
        ));
    }
}
