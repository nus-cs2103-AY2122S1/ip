package duke.command;

import duke.io.UserOutputHandler;
import duke.messages.TaskListMessage;
import duke.tasks.TaskList;

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
     * @param userOutputHandler handles outputting messages to the output destination.
     * @param taskList          handles task operations including adding, deleting, marking as done and retrieval.
     */
    @Override
    public void execute(UserOutputHandler userOutputHandler, TaskList taskList) {
        userOutputHandler.writeMessage(new TaskListMessage(taskList.getAllTasks()));
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
