package duke.commands;

import duke.exceptions.CompletedTaskException;
import duke.exceptions.DukeFileException;
import duke.exceptions.TaskNotFoundException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This is a DoneCommand class that extends Command.
 */
public class DoneCommand extends Command {

    /**
     * This is the constructor of a DoneCommand.
     *
     * @param index An int representing the index of task to be marked done.
     */
    public DoneCommand(int index) {
        super("done", index);
    }

    @Override
    public String execute(TaskList taskList, Storage store, Ui ui)
            throws DukeFileException, CompletedTaskException, TaskNotFoundException {
        if (taskList.getSize() - 1 >= this.index && this.index >= 0) {
            Task taskDone = taskList.markTask(this.index, store);
            return ui.showMarkTaskAsDoneMessage(taskDone);
        } else {
            throw new TaskNotFoundException(this.index + 1);
        }
    }

    @Override
    public String toString() {
        return this.command + " " + (this.index + 1);
    }
}
