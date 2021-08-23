package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasktypes.Task;
import duke.tasktypes.TaskList;

/**
 * Command that deletes task.
 */
public class DeleteCommand extends Command {

    private int numToBeRemoved;

    public DeleteCommand(int numToBeRemoved) {
        this.numToBeRemoved = numToBeRemoved;
    }

    /**
     * Executes the command.
     * @param taskList taskList with all tasks.
     * @param ui User Interface to deal with interactions with user.
     * @param storage Storage to store data of user.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (numToBeRemoved < 0 || numToBeRemoved > taskList.getSize()) {
                ui.displayWrongCommand();
            } else {
                Task removedTask = taskList.get(numToBeRemoved);
                taskList.remove(numToBeRemoved);
                ui.displayDelete(removedTask, taskList);
            }
        } catch (Exception e) {
                ui.showError(e);
        }
    }
}
