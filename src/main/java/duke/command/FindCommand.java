package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * This class finds tasks using a search keyword
 */
public class FindCommand implements Command {

    private String keyword;
    private ArrayList<Task> filtered;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
        this.filtered = new ArrayList<>();
    }

    /**
     *
     * @param taskList Manages all current tasks
     * @param ui Used to print messages
     * @param storage Loads and saves tasks to a txt file
     * @throws DukeException thrown if there are input/parsing errors
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> tasks = taskList.getTasks();
        for(int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDetail().toLowerCase().contains(keyword)) {
                filtered.add(tasks.get(i));
            }
        }
        ui.printAll(filtered, "Here are the matching tasks in your list:");
    }

    /**
     * Method to determine if Duke should stop running.
     *
     * @return false as this is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
