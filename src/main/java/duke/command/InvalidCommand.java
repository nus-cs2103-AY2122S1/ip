package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents how Duke responds to a invalid commands.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class InvalidCommand extends Command {
    /**
     * Returns a string representing Duke's response to invalid commands.
     *
     * @param taskList The taskList where all tasks are stored.
     * @param ui An instance of the Ui class that is responsible for Duke's user interactions.
     * @param storage An instance of a the Storage class that saves and loads Duke's data.
     * @return A string representing Duke's reply after executing this command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null;
        assert storage != null;
        return ui.showInvalidCommandMessage();
    }
}
