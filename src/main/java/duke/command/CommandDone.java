package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Handles marking task as done in Duke.
 */
public class CommandDone extends Command {
    /**
     * The task number that is to be marked as done
     */
    private final int taskNumber;

    /**
     * Constructor for the DoneCommand.
     *
     * @param taskNumber The task number that is to be marked as done.
     */
    public CommandDone(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskList The TaskList that is saved in Duke.
     * @param ui       The Ui of Duke.
     * @param storage  The Storage of Duke.
     * @return String from UI.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.displayDoneUi(taskList.done(taskNumber));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof CommandDone) {
            CommandDone o = (CommandDone) obj;
            return this.taskNumber == o.taskNumber;
        }

        return false;
    }
}
