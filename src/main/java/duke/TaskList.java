package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Contains an ArrayList of the user's Tasks, as well as
 * necessary methods for interaction.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * The constructor for a TaskList object when an
     * existing file is loaded from Storage.
     *
     * @param tasks The list of tasks from Storage.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * The constructor for a TaskList object when there
     * is no existing file.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param task The Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a Task from the TaskList
     *
     * @param index The index of the Task to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves a Task object from the TaskList.
     *
     * @param index The index of the Task to be retrieved.
     * @return The retrieved Task.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the size of the entire TaskList.
     *
     * @return The size of the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks a Task as 'done' on the TaskList.
     *
     * @param index The index of the Task to be marked.
     */
    public void doneTask(int index) {
        tasks.get(index).setDone();
    }
}
