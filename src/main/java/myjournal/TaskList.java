package myjournal;

import java.util.ArrayList;

import myjournal.task.Deadline;
import myjournal.task.Event;
import myjournal.task.Task;

/**
 * Creates the TaskList object.
 *
 * @author Felissa Faustine.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs the TaskList object.
     *
     * @param tasks The list of the tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs the TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds the task to the list of tasks.
     *
     * @param task The task that needs to be added into the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task to be retrieved.
     *
     * @param index The index of the task to be retrieved.
     * @return The task retrieved.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes a task.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the string representative of the TaskList.
     *
     * @return The string representative of the TaskList.
     */
    @Override
    public String toString() {
        String newFile = "";
        for (int i = 0; i < this.getSize(); i++) {
            newFile = newFile + tasks.get(i).getSymbol() + "|" + (tasks.get(i).isDone() ? "1" : "0") + "| "
                    + tasks.get(i).getTaskName()
                    + ((tasks.get(i) instanceof Deadline || tasks.get(i) instanceof Event)
                    ? "|" + tasks.get(i).getTime() : "") + "\n";
        }
        return newFile;
    }
}
