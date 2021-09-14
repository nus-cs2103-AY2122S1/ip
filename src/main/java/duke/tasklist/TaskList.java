package duke.tasklist;

import java.util.ArrayList;

import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents all the tasks created through Duke.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns all the tasks in an array list.
     *
     * @return ArrayList containing all the list.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Adds the task to the list and prints the added task.
     *
     * @param task the task that will be added to the list
     * @return the string representing the success message.
     */
    public String addTask(Ui ui, Task task) {
        this.tasks.add(task);
        return ui.returnMessage(String.format("Got it. I've added this task:%n  %s%nNow you have %d tasks in the list.",
                task, this.tasks.size()));
    }

    /**
     * Deletes the task with taskNo specified.
     *
     * @param taskNo the taskNo of the task to be deleted.
     * @return the string representing the success message.
     */
    public String deleteTask(Ui ui, int taskNo) {
        Task task = this.tasks.get(taskNo - 1);
        this.tasks.remove(taskNo - 1);
        return ui.returnMessage(String.format("Noted. I've removed this task:%n  %s%nNow you have %d tasks "
                + "in the list.", task, this.tasks.size()));
    }

    /**
     * Marks the task with taskNo specified.
     *
     * @param taskNo the taskNo of the task to be marked as done.
     * @return the string representing the success message.
     */
    public String markTaskAsDone(Ui ui, int taskNo) {
        Task task = this.tasks.get(taskNo - 1);
        task.markAsDone();
        return ui.returnMessage(String.format("Nice! I've marked this task as done:%n  %s", task));
    }
}
