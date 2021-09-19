package duke.command;

import duke.data.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * FindCommand represents a command to find a matching term in the task list.
 */
public class FindCommand extends Command {
    /** Attribute of a FindCommand object */
    private String searchTerm;

    /**
     * Initialises a FindCommand object.
     *
     * @param searchTerm the search term the user has keyed in
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Finds the matching tasks and returns a TaskList with all the matched tasks.
     *
     * @param searchTerm the search term
     * @param taskList the current task list
     * @return a TaskList with all matching tasks
     */
    private TaskList findMatchingTasks(String searchTerm, TaskList taskList) {
        TaskList listWithMatchingTasks = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.getTask(i);
            String currentTaskName = currentTask.getTaskName();
            int isSearchTermPresent = currentTaskName.indexOf(searchTerm);
            if (isSearchTermPresent >= 0) {
                listWithMatchingTasks.addTask(currentTask);
            }
        }
        return listWithMatchingTasks;
    }

    /**
     * Executes the FindCommand object.
     *
     * @param taskList the current task list
     * @param ui the ui object used
     * @param storage the current storage
     * @return a string message of the list of matching tasks
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList listWithMatchingTasks = findMatchingTasks(searchTerm, taskList);
        return ui.showMatchingTasksMessage(listWithMatchingTasks);
    }
}
