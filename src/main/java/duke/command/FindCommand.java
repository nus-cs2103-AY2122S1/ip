package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Class to find tasks using a search keyword.
 */
public class FindCommand implements Command {

    private String keyword;
    private ArrayList<Task> filtered;

    /**
     * Method to initialize Find Command.
     *
     * @param keyword Keyword to search for relevant tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
        this.filtered = new ArrayList<>();
    }

    /**
     * Method to execute command.
     *
     * @param taskList TaskList that manages all current tasks.
     * @param ui Ui used to print messages.
     * @param storage Loads and saves the tasks to a txt file.
     * @throws DukeException Thrown if there are input/parsing errors.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDetail().toLowerCase().contains(keyword)) {
                filtered.add(tasks.get(i));
            }
        }
        ui.printAll(filtered, "Here are the matching tasks in your list");
    }

    /**
     * Returns a boolean to determine if Duke should stop running.
     *
     * @return A boolean false as this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
