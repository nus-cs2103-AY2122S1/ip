package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a command to save.
 */
public class SaveCommand implements Command {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Creates a save command.
     *  @param storage Storage
     * @param tasks Task list
     * @param ui Ui
     */
    public SaveCommand(Storage storage, TaskList tasks, Ui ui) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Runs the save command.
     *
     * @return Success message
     */
    @Override
    public String run() throws DukeException {
        storage.save(tasks);
        return ui.showSavedMessage();
    }
}
