package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {

    /**
     * Exits the program.
     *
     * @param tasks Task List that store all current task.
     * @param ui Ui object to interact with user.
     * @param storage Storage that store tasks in hard-drive.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
        System.exit(0);
    }

    /**
     * Checks whether the command is an exit command.
     *
     * @return Boolean whether command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
