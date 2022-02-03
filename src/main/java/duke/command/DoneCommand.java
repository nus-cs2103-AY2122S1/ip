package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     *
     * @param taskList the TaskList used during execution.
     * @param storage the Storage used during execution.
     * @param ui the Ui used during execution.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            String doneString = taskList.markAsDone(index);
            storage.rewrite(taskList);
            return ui.getDoneMessage(doneString);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    /**
     * Checks whether the command is the exit command.
     *
     * @return Whether the command is the exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
