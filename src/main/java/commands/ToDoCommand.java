package commands;

import storage.Storage;
import tasks.TaskList;
import tasks.ToDoTask;
import ui.Ui;

import java.util.ArrayList;

/**
 * The ToDoCommand Class inherits Command and is
 * a specific type of executable command.
 */

public final class ToDoCommand extends Command{

    /**
     * Constructs the ToDoCommand object.
     *
     * @param s the entire line of user input
     */
    public ToDoCommand(ArrayList<String> s) {
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
        if (getInput().size() < 2) {
            return "Oops, you have left out the task description for todo!";
        } else {
            ToDoTask t = new ToDoTask(lst.filterInfo(getInput()));
            String result = lst.addTask(t);
            storage.resetFile(lst.getTasks());
            return result;
        }
    }
}
