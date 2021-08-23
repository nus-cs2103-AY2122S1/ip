package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasktypes.Task;
import duke.tasktypes.TaskList;

public class DeleteCommand extends Command {

    private int numToBeRemoved;

    public DeleteCommand(int numToBeRemoved) {
        this.numToBeRemoved = numToBeRemoved;
    }

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
