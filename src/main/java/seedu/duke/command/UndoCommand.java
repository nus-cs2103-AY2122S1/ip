package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.TaskList;

public class UndoCommand extends Command {
    private static final String UNDO_MESSAGE = "Undid previous command.";
    private Storage storage;

    /**
     * Public constructor for a <code>GetCommand</code>.
     *
     * @param ui The Ui to handle user interactions.
     * @param taskList The task list to be updated.
     * @param ss The snapshot depicting the current state
     */
    public UndoCommand(Ui ui, TaskList taskList, Storage storage) {
        super(ui, taskList);
        this.storage = storage;
    }

    @Override
    public String getUsageMessage() {
        return "undo | undo previous command";
    }

    @Override
    public boolean isUpdatesTaskList() {
        return true;
    }

    /**
     * Returns tasks happening or due on the given date.
     */
    @Override
    public String execute() throws DukeException {
        taskList = taskList.getPrevTaskList();
        storage.undo(taskList);
        return UNDO_MESSAGE;
    }

}

