package commands;

import java.util.ArrayList;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The ByeCommand Class inherits Command and is
 * a specific type of executable command.
 */
public final class ByeCommand extends Command {

    /**
     * Constructs the ByeCommand object.
     *
     * @param userInput the entire line of user input
     */
    public ByeCommand(ArrayList<String> userInput) {
        super(userInput);
    }

    /**
     * Executes the command.
     *
     * @param list the TaskList object that stores the list of tasks
     * @param ui the Ui object that interacts with the user
     * @param storage the Storage object that saves changes to stored tasks, if any
     * @return the message displaying the result
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        assert list != null : "invalid TaskList object detected";
        assert ui != null : "invalid Ui object detected";
        assert storage != null : "invalid Storage object detected";
        return "";
    }
}
