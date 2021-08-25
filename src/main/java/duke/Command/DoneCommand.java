package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(String input) throws DukeException {
        try {
            this.index = Integer.parseInt(input.substring(5));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Invalid Task. Please try again.\n");
        }
    }

    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markTask(index - 1);
        ui.taskMarked(taskList.getTask(index - 1));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
