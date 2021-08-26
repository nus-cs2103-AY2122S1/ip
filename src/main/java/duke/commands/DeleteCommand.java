package duke.commands;

import duke.exceptions.DukeFileException;
import duke.exceptions.TaskNotFoundException;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * This is a DeleteCommand class that extends Command.
 */
public class DeleteCommand extends Command {

    /**
     * This is the constructor of a DeleteCommand.
     *
     * @param index An int representing the index of task to be deleted.
     */
    public DeleteCommand(int index) {
        super("delete", index);
    }

    @Override
    public void execute(TaskList taskList, Storage store, Ui ui)
            throws DukeFileException, TaskNotFoundException {
        if (taskList.getSize() - 1 >= this.index && this.index >= 0) {
            taskList.deleteTask(this.index, store, ui);
        } else {
            throw new TaskNotFoundException(this.index + 1);
        }
    }

    @Override
    public String toString() {
        return this.command + " " + (this.index + 1);
    }
}
