package agent.command;

import agent.exceptions.InvalidTaskNumberException;
import agent.exceptions.TaskFileIoException;
import agent.messages.Message;
import agent.messages.MessageConstants;
import agent.messages.TaskDoneMessage;
import agent.tasks.Task;
import agent.tasks.TaskList;

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
     * @param taskList handles task operations including adding, deleting, marking as done and retrieval.
     * @return response message by chat bot when trying to mark a task as done.
     * @throws TaskFileIoException        thrown when failure due to reading or writing to task save file occurs.
     * @throws InvalidTaskNumberException thrown when the task associated with the given number is not found.
     */
    @Override
    public String execute(TaskList taskList)
            throws InvalidTaskNumberException, TaskFileIoException {
        try {
            // user input is 1 greater than index.
            int index = Integer.parseInt(super.getUserInputBody()) - 1;
            Task doneTask = taskList.setDone(index);
            return new TaskDoneMessage(doneTask).toString();
        } catch (NumberFormatException nfe) {
            return new Message(MessageConstants.MESSAGE_INVALID_INTEGER).toString();
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
