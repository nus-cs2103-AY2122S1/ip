package duke.command;

import duke.io.ResponseManager;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskManager;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * This class encapsulates the command to find a task.
 * It is triggered by the parser and uses the TaskManager, Ui, and Storage.
 */
public class FindTasksCommand implements ICommand {

    private String keyword;
    private String reply;

    /**
     * Constructor for a command to find a task, (i.e when a user attempts
     * to search for a task in the list of tasks.
     *
     * @param input Search keyword entered by the user.
     */
    public FindTasksCommand(String input) {
        keyword = input.substring(5);
    }

    /**
     * Filters the current task list and passes it to the response manager
     * to be displayed.
     *
     * @param tm The TaskManager of this instance of Duke.
     * @param responseManager The ResponseManager of this instance of Duke.
     * @param storage The Storage of this instance of Duke.
     */
    @Override
    public void execute(TaskManager tm, ResponseManager responseManager, Storage storage) {
        ArrayList<Task> originalTasks = new ArrayList<Task>();
        originalTasks.addAll(tm.getTasks());

        ArrayList<Task> filteredTasks = (ArrayList<Task>) originalTasks.stream()
                .filter(task -> task.getName().contains(keyword)).collect(Collectors.toList());

        reply = responseManager.getListTasksMessage(filteredTasks);
    }

    public String getReply() {
        return reply;
    }
}
