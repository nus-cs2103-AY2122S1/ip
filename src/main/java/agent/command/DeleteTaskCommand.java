package agent.command;

import agent.exceptions.InvalidTaskNumberException;
import agent.exceptions.TaskFileIoException;
import agent.messages.Message;
import agent.messages.MessageConstants;
import agent.messages.TaskDeleteMessage;
import agent.tasks.Task;
import agent.tasks.TaskList;

/**
 * Represents a user command to delete the specified <code>Task</code> for persisted Tasks.
 *
 * @author kevin9foong
 */
public class DeleteTaskCommand extends Command {
    /**
     * Constructs instance of <code>DeleteTaskCommand</code> which when executed deletes the task
     * number specified by the user input.
     *
     * @param userInputBody <code>String</code> which includes task number to delete.
     */
    public DeleteTaskCommand(String userInputBody) {
        super(userInputBody);
    }

    /**
     * Deletes the <code>Task</code> with the given task number.
     *
     * @param taskList handles task operations including adding, deleting, marking as done and retrieval.
     * @return response message by chat bot when trying to delete a task.
     * @throws TaskFileIoException        thrown when failure due to reading or writing to task save file occurs.
     * @throws InvalidTaskNumberException thrown when the task associated with the given number is not found.
     */
    @Override
    public String execute(TaskList taskList) throws
            TaskFileIoException, InvalidTaskNumberException {
        try {
            // user input is 1 greater than index.
            int index = Integer.parseInt(super.getUserInputBody()) - 1;
            Task deletedTask = taskList.deleteTask(index);
            return new TaskDeleteMessage(deletedTask.toString(), taskList.getNumOfTasks()).toString();
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
