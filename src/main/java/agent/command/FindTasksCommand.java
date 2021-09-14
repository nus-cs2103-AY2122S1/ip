package agent.command;

import java.util.List;
import java.util.stream.Collectors;

import agent.exceptions.EmptyFindBodyException;
import agent.messages.TaskFindListMessage;
import agent.tasks.Task;
import agent.tasks.TaskList;

/**
 * Represents user command to find all tasks with contains specified text filter String
 * in its description.
 *
 * @author kevin9foong
 */
public class FindTasksCommand extends Command {

    /**
     * Constructs instance of <code>FindTasksCommand</code> which searches within the list of
     * persisted tasks for tasks which contain the specified user input search query string within
     * its description.
     *
     * @param userInputBody <code>String</code> which includes the desired task description search query.
     */
    public FindTasksCommand(String userInputBody) {
        super(userInputBody);
    }

    /**
     * Returns all Tasks which contain the text filter String in its description.
     *
     * @param taskList handles task operations including adding, deleting, marking as done and retrieval.
     * @return response message by chat bot when trying to find tasks.
     * @throws EmptyFindBodyException thrown when provided search query is empty.
     */
    @Override
    public String execute(TaskList taskList) throws EmptyFindBodyException {
        String textFilter = super.getUserInputBody();
        if (textFilter == null) {
            throw new EmptyFindBodyException();
        }
        List<Task> matchedTasks = taskList.getAllTasks().stream().filter(task ->
                task.getDescription().contains(textFilter)).collect(Collectors.toList());

        return new TaskFindListMessage(matchedTasks).toString();
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
