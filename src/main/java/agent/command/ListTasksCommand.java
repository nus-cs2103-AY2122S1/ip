package agent.command;

import agent.messages.TaskListMessage;
import agent.tasks.TaskList;

/**
 * Represents user command to display all tasks that have been persisted.
 *
 * @author kevin9foong
 */
public class ListTasksCommand extends Command {

    /**
     * Constructs an instance of <code>ListTasksCommand</code> which outputs a message
     * displaying the details of all currently persisted tasks.
     *
     * @param userInputBody not being utilised.
     */
    public ListTasksCommand(String userInputBody) {
        super(userInputBody);
    }

    /**
     * Retrieves all tasks from the <code>TaskList</code> and writes message to user to
     * display all tasks in the <code>TaskList</code>.
     *
     * @param taskList handles task operations including adding, deleting, marking as done and retrieval.
     * @return response message by chat bot when trying to list tasks stored.
     */
    @Override
    public String execute(TaskList taskList) {
        return new TaskListMessage(taskList.getAllTasks()).toString();
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
