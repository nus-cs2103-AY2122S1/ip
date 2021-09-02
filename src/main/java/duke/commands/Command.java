package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList The list of tasks in Duke. Handles all task related functions.
     * @param ui Ui object to deal with user input/outputs.
     * @param storage Storage object to deal with saving taskList to disk.
     * @throws DukeException Any exception relating specifically with Duke or its components.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public boolean hasExited() {
        return false;
    }
}
