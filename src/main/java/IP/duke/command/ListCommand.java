package IP.duke.command;

import IP.duke.main.Storage;
import IP.duke.main.TaskList;
import IP.duke.main.Ui;

/**
 * Represents a command to list out the tasks Duke.Duke is keeping track of.
 * 
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class ListCommand extends Command {
    private boolean isExitCommand;

    /**
     * Class constructor.
     */
    public ListCommand() {
        isExitCommand = false;
    }

    /**
     * Executes the command to list out all tasks Duke.Duke is keeping track of.
     * 
     * @param tasks lists of tasks
     * @param ui the user interface.
     * @param storage the storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showListOfTasks(tasks.getTasks());
    }
}
