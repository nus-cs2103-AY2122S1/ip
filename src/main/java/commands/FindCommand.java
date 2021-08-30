package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * The FindCommand Class inherits Command and is
 * a specific type of executable command.
 */
public final class FindCommand extends Command{

    /**
     * Constructs the FindCommand object.
     *
     * @param s the entire line of user input
     */
    public FindCommand(ArrayList<String> s) {
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
        String target = "";
        for (int i = 1; i < getInput().size(); i++) {
            if (i + 1 < getInput().size()) {
                target += getInput().get(i) + " ";
            } else {
                target += getInput().get(i);
            }
        }
        ArrayList<Task> tasksFound = lst.findTask(target);
        if (tasksFound.isEmpty()) {
            return "No task tasks found!";
        } else {
            String temp = "The tasks found are: \n";
            for (int i = 0; i < tasksFound.size(); i++) {
                if (i + 1 < tasksFound.size()) {
                    temp += "     " + (i + 1) + "." + tasksFound.get(i).getType()
                            + tasksFound.get(i).getStatus() + " " + tasksFound.get(i).getTask() + "\n";
                } else {
                    temp += "     " + (i + 1) + "." + tasksFound.get(i).getType()
                            + tasksFound.get(i).getStatus() + " " + tasksFound.get(i).getTask();
                }
            }
            return temp;
        }
    }
}
