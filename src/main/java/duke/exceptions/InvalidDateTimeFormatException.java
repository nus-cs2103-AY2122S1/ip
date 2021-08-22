package duke.exceptions;

import duke.messages.MessageConstants;

/**
 * Represents an <code>Exception</code> due to unsupported format of <code>DateTime</code> String provided.
 *
 * @author kevin9foong
 */
public class InvalidDateTimeFormatException extends DukeException {
    public InvalidDateTimeFormatException() {
        super(MessageConstants.MESSAGE_INVALID_DATETIME_FORMAT);
    }
}
