package duke.commands;

import duke.exceptions.DukeException;
import duke.main.Ui;
import duke.main.Storage;
import duke.main.TaskList;

/**
 * Represents command to list all tasks
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    public ListCommand(String userInput) throws DukeException {
        super(userInput);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        //Ui
        ui.showTaskList(taskList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}