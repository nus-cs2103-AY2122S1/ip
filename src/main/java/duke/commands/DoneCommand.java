package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasktypes.Task;
import duke.tasktypes.TaskList;

/**
 * Command that marks a task as done.
 */
public class DoneCommand extends Command {

    private int numToBeMarked;

    public DoneCommand(int numToBeMarked) {
        this.numToBeMarked = numToBeMarked;
    }

    /**
     * Executes the command.
     * @param taskList taskList with all tasks.
     * @param ui User Interface to deal with interactions with user.
     * @param storage Storage to store data of user.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (numToBeMarked < 0 || numToBeMarked > taskList.getSize()) {
                ui.displayWrongCommand();
            } else {
                Task done = taskList.get(numToBeMarked);
                taskList.get(numToBeMarked).markAsDone();
                ui.displayDone(done, taskList);
            }
        } catch (Exception e) {
            ui.showError(e);
        }
    }

}
