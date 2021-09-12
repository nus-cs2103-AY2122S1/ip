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
        if (getInput().size() < 2) {
            return "     Oops, you have left out the task description for todo!";
        }
        ToDoTask task = new ToDoTask(list.filterInfo(getInput()));
        String result = list.addTask(task);
        storage.resetFile(list.getTasks());
        return result;
    }
}
