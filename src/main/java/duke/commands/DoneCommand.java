package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasktypes.Task;
import duke.tasktypes.TaskList;

/**
 * Command that marks a task as done.
 */
public class DoneCommand extends Command {

    private final int numToBeMarked;

    public DoneCommand(int numToBeMarked) {
        this.numToBeMarked = numToBeMarked;
    }

    /**
     * Executes the command.
     *
     * @param taskList taskList with all tasks.
     * @param ui User Interface to deal with interactions with user.
     * @param storage Storage to store data of user.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null;
        assert ui != null;
        assert storage != null;
        try {
            if (numToBeMarked < 0 || numToBeMarked > taskList.getSize()) {
                return ui.displayWrongCommand();
            } else {
                Task done = taskList.get(numToBeMarked);
                taskList.get(numToBeMarked).markAsDone();
                storage.updateHardDisk(taskList);
                return ui.displayDone(done, taskList);
            }
        } catch (Exception e) {
            return ui.showError(e);
        }
    }
}
