package duke.command;

import duke.exceptions.InvalidTaskNumberException;
import duke.exceptions.TaskFileIOException;
import duke.io.UserOutputHandler;
import duke.messages.Message;
import duke.messages.MessageConstants;
import duke.messages.TaskDoneMessage;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * Represents user command to mark specified task as done.
 *
 * @author kevin9foong
 */
public class MarkTaskDoneCommand extends Command {

    /**
     * Constructs an instance of <code>MarkTaskDoneCommand</code> which when executed sets the task
     * with the specified task number as done.
     *
     * @param userInputBody <code>String</code> which includes the task number of task to mark as done.
     */
    public MarkTaskDoneCommand(String userInputBody) {
        super(userInputBody);
    }

    /**
     * Marks the specified <code>Task</code> as done and writes to user to indicate that <code>Task</code> has been
     * successfully marked as done.
     *
     * @param userOutputHandler handles outputting messages to the output destination.
     * @param taskList          handles task operations including adding, deleting, marking as done and retrieval.
     * @throws TaskFileIOException        thrown when failure due to reading or writing to task save file occurs.
     * @throws InvalidTaskNumberException thrown when the task associated with the given number is not found.
     */
    @Override
    public void execute(UserOutputHandler userOutputHandler, TaskList taskList)
            throws InvalidTaskNumberException, TaskFileIOException {
        try {
            // user input is 1 greater than index.
            int index = Integer.parseInt(super.getUserInputBody()) - 1;
            Task doneTask = taskList.setDone(index);
            userOutputHandler.writeMessage(new TaskDoneMessage(doneTask));
        } catch (NumberFormatException nfe) {
            userOutputHandler.writeMessage(new Message(MessageConstants.MESSAGE_INVALID_INTEGER));
        }
    }

    /**
     * Returns false to indicate program should not terminate after command is executed.
     *
     * @return false to indicate program should not terminate after command is executed.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
