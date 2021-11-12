package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a list containing all the tasks.
 * A TaskList contains an ArrayList of the tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList Object containing an empty ArrayList.
     */
    public TaskList() {
        this(new ArrayList<Task>());
    }

    /**
     * Creates a TaskList Object containing the ArrayList of tasks given.
     * @param tasks ArrayList of tasks to be stored in TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Deletes a task from the TaskList stored in the index given.
     * @param index index of the task to be deleted.
     */
    public void delete(int index) {
        assert index < getSize() : "index should be within range";
        tasks.remove(index);
    }

    /**
     * Adds the Task given to the TaskList.
     * @param t Task to be added to the TaskList.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Returns the Task from the TaskList stored in the index given.
     * @param index index of the task to be retrieved.
     * @return Task stored at the index.
     */
    public Task get(int index) {
        assert index < getSize() : "index should be within range";
        return tasks.get(index);
    }

    /**
     * Returns the number of Tasks stored in the TaskList.
     * @return Number of Tasks stored in the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }
}