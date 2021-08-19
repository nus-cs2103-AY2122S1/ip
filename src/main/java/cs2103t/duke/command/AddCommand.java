package cs2103t.duke.command;

import cs2103t.duke.task.Task;
import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (taskList.addTask(task)) {
            ui.displayText(space + "Got it. I've added this task: \n"
                    + space + "  " + task.getDescriptionWithStatus() + "\n"
                    + space + "Now you have " + taskList.getNumOfTasks() + " tasks in the list.");
            // dataHandler.storeTaskList(taskList);
        } else {
            System.exit(1);
        }
    }

}
