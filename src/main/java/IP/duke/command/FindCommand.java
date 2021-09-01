package IP.duke.command;
import IP.duke.main.Date;
import IP.duke.main.Storage;
import IP.duke.main.TaskList;
import IP.duke.main.Ui;
import IP.duke.task.Task;

import java.util.ArrayList;

import javax.swing.*;

/**
 * Represents a command to filter out tasks that fall on a specific date.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class FindCommand extends Command {
    private final String KEYWORRD = "find ";
    private String searchPhrase;
    private boolean isExitCommand;
    /**
     * Class constructor.
     *
     * @param userCommand to filter out tasks containing a searchPhrase.
     * @throws StringIndexOutOfBoundsException if there is no search phrase.
     */
    public FindCommand(String userCommand) throws StringIndexOutOfBoundsException {
        int startingIndex = userCommand.indexOf(KEYWORRD) + KEYWORRD.length();
        this. searchPhrase = userCommand.substring(startingIndex);
        if(searchPhrase.equals("")) {
            throw new StringIndexOutOfBoundsException();
        }
        isExitCommand = false;
    }

    /**
     * Executes a command to filter out tasks falling on the specified date.
     * @param tasks lists of tasks
     * @param ui the user interface.
     * @param storage the storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findMatchingTasks(searchPhrase);
        String message = "Here are the matching tasks in your list:";
        ui.showMatchingTasks(matchingTasks, searchPhrase, message);
    }
}
