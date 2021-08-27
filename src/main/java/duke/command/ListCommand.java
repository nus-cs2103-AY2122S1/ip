package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        printTasks(taskList);
    }

    private void printTasks(TaskList tasks) {
        String message = "Here are the tasks in your list:";
        System.out.println(message);
        for (int i = 1; i <= tasks.size(); i++) {
            Task currentTask = tasks.getTask(i - 1);
            String displayedTask = i + "." + currentTask.toString();
            System.out.println(displayedTask);
        }
    }
}
