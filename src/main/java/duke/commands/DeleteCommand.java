package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasktypes.Task;
import duke.tasktypes.TaskList;

/**
 * Command that deletes task.
 */
public class DeleteCommand extends Command {

    private final int numToBeRemoved;

    public DeleteCommand(int numToBeRemoved) {
        this.numToBeRemoved = numToBeRemoved;
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
            if (numToBeRemoved < 0 || numToBeRemoved > taskList.getSize()) {
                return ui.displayWrongCommand();
            } else {
                Task removedTask = taskList.get(numToBeRemoved);
                taskList.remove(numToBeRemoved);
                storage.updateHardDisk(taskList);
                return ui.displayDelete(removedTask, taskList);
            }
        } catch (Exception e) {
            return ui.showError(e);
        }
    }
}
