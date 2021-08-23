package commands;

import duke.Storage;
import duke.Ui;
import tasktypes.Task;
import tasktypes.TaskList;

public class DoneCommand extends Command {

    private int numToBeMarked;

    public DoneCommand(int numToBeMarked) {
        this.numToBeMarked = numToBeMarked;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (numToBeMarked < 0 || numToBeMarked > taskList.getSize()) {
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
