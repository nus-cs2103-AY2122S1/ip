package commands;
import tasks.Task;
import tasks.TaskList;

public abstract class AddCommand extends Command {
    protected Task task;

    public String execute(TaskList taskList) {
        taskList.addTask(task);
        int numTasks = taskList.getNumOfTasks();
        return "Got it. I've added this task:\n\t" + this.task
                + "\nNow you have " + numTasks + " tasks in the list.";
    }
}