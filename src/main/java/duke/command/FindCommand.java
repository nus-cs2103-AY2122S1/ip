package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to find tasks with descriptions matching search string
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String USAGE_TEXT = "Usage: find [key words]";

    /** String to search for in list of tasks */
    private String searchString;

    /**
     * FindCommand constructor.
     *
     * @param arg String to search for in list of tasks.
     */
    public FindCommand(String arg) {
        searchString = arg;
    }

    /**
     * Displays results of tasks matching search string.
     *
     * @param taskList List of tasks.
     * @param ui User interface.
     * @param storage Storage of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList results = TaskList.emptyTaskList();

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(searchString)) {
                results.addTask(task);
            }
        }

        return ui.reply("These the tasks yer lookin for:\n" + results.toString());
    }
}
