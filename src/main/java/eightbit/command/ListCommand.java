package eightbit.command;

import eightbit.util.Storage;
import eightbit.util.TaskList;
import eightbit.util.Ui;

/**
 * Represents a command to show the list of tasks.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder listOfTasks = new StringBuilder();
        if (taskList.size() == 0) {
            listOfTasks = new StringBuilder("There are no tasks in your list currently.");
            ui.printWithLines(listOfTasks.toString());
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                String newTask = (i + 1) + ". " + taskList.get(i) + "\n";
                listOfTasks.append(newTask);
            }
            ui.printWithLines("Here are the tasks in your list:\n" + listOfTasks);
        }
    }
}
