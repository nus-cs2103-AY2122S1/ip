package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a command to find all tasks containing a search phrase.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class FindCommand extends Command{

    private String searchPhrase;
    private final String FIND_KEYWORD = "find";
    private ArrayList<Task> matchingTasks;

    /**
     * Class constructor.
     * @param command user input command.
     */
    public FindCommand(String command) {
        int startingIndex = command.indexOf(FIND_KEYWORD) + FIND_KEYWORD.length();
        searchPhrase = command.substring(startingIndex + 1);
    }

    /**
     * Executes a command to filter out tasks falling on the specified date.
     *
     * @param tasks lists of tasks
     * @param ui the user interface.
     * @param storage the storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        matchingTasks = tasks.findMatchingTasks(searchPhrase);
        String message = "Here are the matching tasks in your list:";
        ui.showMatchingTasks(matchingTasks, message);
    }
}
