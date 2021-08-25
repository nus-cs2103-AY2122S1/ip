package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command{

    public FindCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parsedUserInput = this.getUserInput().split(" ", 2);
        if (parsedUserInput.length == 1) {
            throw new DukeException("☹ OOPS!!! Please enter \"find\" followed by the name of the task you want to find");
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

    private String addToStringToPrint(int counter, String toDoListToPrint, Task currentTask) {
        return toDoListToPrint + counter + "." + currentTask.toString() + "\n";
    }
}
