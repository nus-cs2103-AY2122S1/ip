package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * The DueCommand Class inherits Command and is
 * a specific type of executable command.
 */

public final class DueCommand extends Command{

    /**
     * Constructs the DueCommand object.
     *
     * @param s the entire line of user input
     */
    public DueCommand(ArrayList<String> s) {
        super(s);
    }

    /**
     * Executes the command.
     *
     * @param lst the TaskList object that stores the list of tasks
     * @param ui the Ui object that interacts with the user
     * @param storage the Storage object that saves changes to stored tasks, if any
     * @return the message displaying the result
     */
    @Override
    public String execute(TaskList lst, Ui ui, Storage storage) {
        try {
            String result = lst.anyTaskDue(getInput().get(1));
            return result;
        } catch (IndexOutOfBoundsException e) {
           return "Invalid input :(\n"
                   + Ui.getHelperMessage();
        }
    }
}
