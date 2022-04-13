package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Handles deleting specific task from Duke.
 */
public class CommandDelete extends Command {
    /**
     * The task number that is to be deleted
     */
    private final int taskNumber;

    /**
     * Constructor for the DeleteCommand.
     *
     * @param taskNumber The task number that is to be deleted.
     */
    public CommandDelete(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes the specified task.
     *
     * @param taskList The TaskList that is saved in Duke.
     * @param ui       The Ui of Duke.
     * @param storage  The Storage of Duke.
     * @return String from UI.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.displayDeleteUi(taskList, taskList.delete(taskNumber));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof CommandDelete) {
            CommandDelete o = (CommandDelete) obj;
            return this.taskNumber == o.taskNumber;
        }

        return false;
    }
}
