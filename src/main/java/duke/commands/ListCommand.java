package duke.commands;

import duke.exceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //Ui
        return ui.showTaskList(taskList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}