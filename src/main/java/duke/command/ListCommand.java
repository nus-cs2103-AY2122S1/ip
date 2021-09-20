package duke.command;

import duke.main.Storage;
import duke.main.Ui;
import duke.task.TaskList;

/**
 * Represents a command to list out the tasks Duke.Duke is keeping track of.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class ListCommand extends Command {

    /**
     * Class constructor.
     */
    public ListCommand() {
        super();
        assert !isExit() : "isExit should return false";
    }

    /**
     * Executes the command to list out all tasks Duke.Duke is keeping track of.
     *
     * @param tasks taskList of tasks
     * @param ui the user interface.
     * @param storage the storage file.
     * @return message listing out all the tasks currently tracked by Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showListOfTasks(tasks, tasks.getNumTasks());
    }
}
