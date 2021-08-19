package duke.commands;

import duke.exceptions.DukeFileException;
import duke.exceptions.TaskIsCompleteException;
import duke.exceptions.TaskNotFoundException;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * This is a duke.commands.DoneCommand class that extends duke.commands.Command.
 */
public class DoneCommand extends Command {

    public DoneCommand(int index) {
        super("done", index);
    }


    @Override
    public void execute(TaskList taskList, Storage store, Ui ui)
            throws DukeFileException, TaskIsCompleteException, TaskNotFoundException {
        if (taskList.getSize() - 1 >= this.index && this.index >= 0) {
            taskList.markTask(this.index, store, ui);
        } else {
            throw new TaskNotFoundException(this.index + 1);
        }

    }
}
