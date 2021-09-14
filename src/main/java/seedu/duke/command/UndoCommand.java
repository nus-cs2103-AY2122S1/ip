package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.TaskList;

/**
 * Represents an undo command. An <code>UndoCommand</code> undoes
 * the latest command done by the user.
 */
public class UndoCommand extends Command {
    private static final String UNDO_MESSAGE = "Undid previous command.";
    private Storage storage;

    /**
     * Public constructor for an <code>UndoCommand</code>.
     *
     * @param ui The Ui to handle user interactions.
     * @param taskList The task list to be updated.
     * @param storage The storage that handles file modifications.
     */
    public UndoCommand(Ui ui, TaskList taskList, Storage storage) {
        super(ui, taskList);
        this.storage = storage;
    }

    /**
     * Returns the format on how to use the command.
     *
     * @return String representation of the help message.
     */
    @Override
    public String getUsageMessage() {
        return "undo | undo previous command";
    }

    /**
     * Check if the given command alters the task list.
     *
     * @return true if it updates the task list.
     */
    @Override
    public boolean isUpdatesTaskList() {
        return true;
    }

    /**
     * Undo the last command.
     */
    @Override
    public String execute() throws DukeException {
        taskList = taskList.getPrevTaskList();
        storage.undo(taskList);
        return UNDO_MESSAGE;
    }

}

