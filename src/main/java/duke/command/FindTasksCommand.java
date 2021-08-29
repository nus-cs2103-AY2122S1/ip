package duke.command;

import java.util.List;
import java.util.stream.Collectors;

import duke.exceptions.EmptyFindBodyException;
import duke.io.UserOutputHandler;
import duke.messages.TaskFindListMessage;
import duke.tasks.Task;
import duke.tasks.TaskList;

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
     * @param userOutputHandler handles outputting messages to the output destination
     * @param taskList          handles task operations including adding, deleting, marking as done and retrieval
     */
    @Override
    public void execute(UserOutputHandler userOutputHandler, TaskList taskList) throws EmptyFindBodyException {
        String textFilter = super.getUserInputBody();
        if (textFilter == null) {
            throw new EmptyFindBodyException();
        }
        List<Task> matchedTasks = taskList.getAllTasks().stream().filter(task ->
                task.getDescription().contains(textFilter)).collect(Collectors.toList());

        userOutputHandler.writeMessage(new TaskFindListMessage(matchedTasks));
    }

    /**
     * Returns false to indicate that program should not be terminated after command is executed.
     *
     * @return false to indicate that program should not be terminated after command is executed.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
