import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    protected TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    protected ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Adds the task to the list and prints the added task.
     *
     * @param task the task that will be added to the list
     */
    protected void addTask(Ui ui, Task task) {
        this.tasks.add(task);
        ui.printsMessage(String.format("Got it. I've added this task:%n  %s%nNow you have %d tasks in the list.",
                task, this.tasks.size()));
    }

    /**
     * Deletes the task with taskNo specified.
     *
     * @param taskNo the taskNo of the task to be deleted.
     */
    protected void deleteTask(Ui ui, int taskNo) {
        Task task = this.tasks.get(taskNo - 1);
        this.tasks.remove(taskNo - 1);
        ui.printsMessage(String.format("Noted. I've removed this task:%n  %s%nNow you have %d tasks in the list.",
                task, this.tasks.size()));
    }

    /**
     * Deletes the task with taskNo specified.
     *
     * @param taskNo the taskNo of the task to be deleted.
     */
    protected void markTaskAsDone(Ui ui, int taskNo) {
        Task task = this.tasks.get(taskNo - 1);
        task.markAsDone();
        ui.printsMessage(String.format("Nice! I've marked this task as done:%n  %s", task));
    }
}
