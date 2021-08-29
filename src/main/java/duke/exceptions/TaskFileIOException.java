package duke.exceptions;

import duke.messages.MessageConstants;

/**
 * Represents an <code>Exception</code> due to failure to read/write from the task save file.
 *
 * @author kevin9foong
 */
public class TaskFileIOException extends DukeException {
    public TaskFileIOException() {
        super(MessageConstants.MESSAGE_TASK_FILE_IO_FAILURE);
    }
}
