package duke;

import duke.task.Task;
import java.util.ArrayList;

/**
 * This class contains the tasks loaded from the file.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the current tasks in the list
     * @return The current tasks in the list
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Marks a task as done
     * @param n Index of the task to mark as done
     * @return A message to the user that the task has been marked as done
     */
    public String markAsDone(int n) {
        Task task = this.tasks.get(n-1);
        task.markAsDone();
        return Ui.doneTask(task);
    }

    /**
     * Adds a task
     * @param task Task to be added to the list
     * @return A message to the user that the task has been added to the list
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return Ui.addTask(task, this.tasks.size());
    }

    /**
     * Deletes a task
     * @param n Index of the task to be deleted
     * @return A message to the user that the task has been deleted
     */
    public String deleteTask(int n) {
        Task task = this.tasks.get(n-1);
        this.tasks.remove(n-1);
        return Ui.deleteTask(task, this.tasks.size());
    }

    /**
     * Finds the tasks matching a given string
     * @param search Input string entered by the user
     * @return A message to the user containing the tasks that match the given search string
     */
    public String findTasks(String search) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDesription().contains(search)) {
                matches.add(task);
            }
        }
        return Ui.findTasks(matches);
    }
}
