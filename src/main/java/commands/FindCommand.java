package commands;

import java.util.ArrayList;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * The FindCommand Class inherits Command and is
 * a specific type of executable command.
 */
public final class FindCommand extends Command {

    /**
     * Constructs the FindCommand object.
     *
     * @param userInput the entire line of user input
     */
    public FindCommand(ArrayList<String> userInput) {
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
        String keyword = "";
        if (getInput().size() < 2) {
            return "     Please input a keyword for me to look for!";
        }
        for (int i = 1; i < getInput().size(); i++) {
            if (i + 1 < getInput().size()) {
                keyword += getInput().get(i) + " ";
            } else {
                keyword += getInput().get(i);
            }
        }
        ArrayList<Task> tasksFound = list.findTask(keyword);
        if (tasksFound.isEmpty()) {
            return "     No task task found!";
        }
        String result = "     The task(s) found are: \n";
        for (int i = 0; i < tasksFound.size(); i++) {
            if (i + 1 < tasksFound.size()) {
                result += "     " + (i + 1) + "." + tasksFound.get(i).getType()
                        + tasksFound.get(i).getStatus() + " " + tasksFound.get(i).getDescription() + "\n";
            } else {
                result += "     " + (i + 1) + "." + tasksFound.get(i).getType()
                        + tasksFound.get(i).getStatus() + " " + tasksFound.get(i).getDescription();
            }
        }
        return result;

    }
}
