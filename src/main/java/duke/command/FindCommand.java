package duke.command;

import duke.data.exceptions.DukeException;
import duke.data.exceptions.InvalidInputException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private static final String FIND = "find";

    private String command;

    public FindCommand(String command) {
        this.command = command;
    }

    private String getSearchTerm() {
        int spaceIndex = command.indexOf(" ");
        return command.substring(spaceIndex + 1);
    }

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

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList listWithMatchingTasks = findMatchingTasks(getSearchTerm(), taskList);
        ui.showTasksWithSearchTerm(listWithMatchingTasks);
    }
}
