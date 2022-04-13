package agent.exceptions;

import agent.messages.MessageConstants;

/**
 * Represents an <code>Exception</code> due to failure to read/write from the task save file.
 *
 * @author kevin9foong
 */
public class TaskFileIoException extends DukeException {
    public TaskFileIoException() {
        super(MessageConstants.MESSAGE_TASK_FILE_IO_FAILURE);
    }
}
