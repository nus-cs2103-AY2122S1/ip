package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * ExitCommand returns good bye message.
 */
public class ExitCommand extends Command {

    /**
     * Constructs ExitCommand object.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * Returns goodbye message.
     *
     * @param tasks Task List that store all current task.
     * @param ui Ui object to interact with user.
     * @param storage Storage that store tasks in hard-drive.
     * @return Goodbye message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        return ui.createExitMessage();
    }


}
