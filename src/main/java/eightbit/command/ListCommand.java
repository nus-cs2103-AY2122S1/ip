package eightbit.command;

import eightbit.util.Storage;
import eightbit.util.TaskList;

/**
 * Represents a command to show the list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Prints the user's list of tasks.
     *
     * @param taskList User's list of tasks.
     * @param storage  Storage responsible for reading/writing the file.
     * @return The response after executing the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        StringBuilder listOfTasks = new StringBuilder();
        if (taskList.size() == 0) {
            listOfTasks = new StringBuilder("There are no tasks in your list currently.");
            return listOfTasks.toString();
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                String newTask = (i + 1) + ". " + taskList.get(i) + "\n";
                listOfTasks.append(newTask);
            }
            return "Here are the tasks in your list:\n" + listOfTasks;
        }
    }
}
