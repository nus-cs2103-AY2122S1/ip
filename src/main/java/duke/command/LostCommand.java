package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * LostCommand tells user that Duke is not able to understand user's input.
 */
public class LostCommand extends Command {

    /**
     * Constructor for Lost Command.
     */
    public LostCommand() {
        this.isExit = false;
    }

    /**
     * Informs user that Duke is unable to understand the command.
     *
     * @param tasks Task List that store all current task.
     * @param ui Ui object to interact with user.
     * @param storage Storage that store tasks in hard-drive.
     * @return Lost message to be sent to user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        return ui.createLostMessage();
    }


}
