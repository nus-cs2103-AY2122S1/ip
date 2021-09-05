package commands;

import java.util.ArrayList;

import storage.Storage;
import tasks.TaskList;
import tasks.ToDoTask;
import ui.Ui;

/**
 * The ToDoCommand Class inherits Command and is
 * a specific type of executable command.
 */

public final class ToDoCommand extends Command {

    /**
     * Constructs the ToDoCommand object.
     *
     * @param userInput the entire line of user input
     */
    public ToDoCommand(ArrayList<String> userInput) {
        super(userInput);
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
        assert lst != null : "invalid TaskList object detected";
        assert ui != null : "invalid Ui object detected";
        assert storage != null : "invalid Storage object detected";
        if (getInput().size() < 2) {
            return "     Oops, you have left out the task description for todo!";
        } else {
            ToDoTask t = new ToDoTask(lst.filterInfo(getInput()));
            String result = lst.addTask(t);
            storage.resetFile(lst.getTasks());
            return result;
        }
    }
}
