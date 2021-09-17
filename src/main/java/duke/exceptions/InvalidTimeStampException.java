package duke.exceptions;

public class InvalidTimeStampException extends DukeException {

    /**
     * InvalidTimeStampException constructor.
     *
     * @param timeStamp Invalid timestamp given.
     */
    public InvalidTimeStampException(String timeStamp) {
        super(String.format(
                "This timestamp \"%s\" is invalid, duke.Duke requires d/M/yyyy HHmm format",
                timeStamp
        ));
    }
}
