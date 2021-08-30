package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents how Duke responds to a "list" command.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class ListCommand extends Command {

    /**
     * Returns a boolean that tells Duke if this is the command to exit.
     *
     * @return A boolean representing the exit condition.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes a "list" command.
     *
     * @param taskList The taskList where all tasks are stored.
     * @param ui An instance of the Ui class that is responsible for Duke's user interactions.
     * @param storage An instance of a the Storage class that saves and loads Duke's data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.showList();
    }
}
