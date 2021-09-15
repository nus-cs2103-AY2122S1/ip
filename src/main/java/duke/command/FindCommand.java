package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {

    /**
     * Constructor for class FindCommand
     *
     * @param userInput  user's input
     */
    public FindCommand(String userInput) {
        super(userInput);
    }

    /**
     * Finds the list of tasks that match the string
     *
     * @param tasks contains the task list
     * @param ui deals with interactions with the user
     * @param storage deals with loading tasks from the file and saving tasks in the file
     * @throws DukeException If the format of the user input is incorrect
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parsedUserInput = this.getUserInput().split(" ", 2);
        if (parsedUserInput.length == 1) {
            throw new DukeException("OOPS!!! Please enter \"find\" followed by the name of the task you want to find");
        } else {
            String taskToFind = parsedUserInput[1];
            String toDoListToPrint = "";
            int counter = 0;
            ArrayList<Task> currentTasks = tasks.getTasks();
            for (int pos = 0; pos < currentTasks.size(); pos++) {
                Task currentTask = currentTasks.get(pos);
                if (currentTask.getTaskName().contains(taskToFind)) {
                    counter = counter + 1;
                    toDoListToPrint = addToStringToPrint(counter, toDoListToPrint, currentTask);
                }
            }
            ui.reply(toDoListToPrint);
        }
    }

    /**
     * Generates the string to print of the list of found items
     *
     * @param counter number of found items
     * @param toDoListToPrint
     * @param currentTask
     * @return
     */
    private String addToStringToPrint(int counter, String toDoListToPrint, Task currentTask) {
        return toDoListToPrint + counter + "." + currentTask.toString() + "\n";
    }
}
