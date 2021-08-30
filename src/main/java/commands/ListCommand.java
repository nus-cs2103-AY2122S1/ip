package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * The ListCommand Class inherits Command and is
 * a specific type of executable command.
 */
public final class ListCommand extends Command{


    /**
     * Constructs the ListCommand object.
     *
     * @param s the entire line of user input
     */
    public ListCommand(ArrayList<String> s) {
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
        return lst.getList();
    }
}
