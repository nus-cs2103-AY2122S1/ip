package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    /**
     * Executes the command.
     *
     * @param taskList the TaskList used during execution.
     * @param storage the Storage used during execution.
     * @param ui the Ui used during execution.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return ui.getListMessage(taskList);
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
