package duke.command;

import duke.exception.InvalidInputException;
import duke.util.Storage;
import duke.util.Ui;
import duke.util.TaskList;

public abstract class Command {

    /**
     * Executes the specified command.
     *
     * @param tasks The TaskList which we are modifying.
     * @param ui The Ui we will use for user interaction.
     * @param storage The Storage we will use for storing save data.
     * @throws InvalidInputException When the input is deemed invalid.
     * @return
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException;

    public abstract boolean isExit();

}
