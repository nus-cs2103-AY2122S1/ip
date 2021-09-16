package duke.util;
import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a RAM for Duke.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the task item from Task List.
     *
     * @param index the index of task
     * @return Task item
     */
    public Task get(int index) {
        if (index > tasks.size() - 1) {
            throw new DukeException("OOPS!!! The index is beyond the scope!");
        }
        return tasks.get(index);
    }

    /**
     * Gets the number of Task items in a Task List
     *
     * @return an integer
     */
    public int length() {
        return tasks.size();
    }

    /**
     * Adds a new Task item into the Task List.
     *
     * @param task the item to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task in the Task List as done.
     *
     * @param index the index of task to be marked
     */
    public void done(int index) {
        Task temp = tasks.get(index);
        temp.markAsDone();
        tasks.set(index, temp);
    }

    /**
     * Marks a task in the Task List with high priority
     *
     * @param index the index of task to be marked
     */
    public void markAsHighPriority(int index) {
        Task temp = tasks.get(index);
        temp.markAsHighPriority();
        tasks.set(index, temp);
    }

    /**
     * Marks a task in the Task List with medium priority
     *
     * @param index the index of task to be marked
     */
    public void markAsMediumPriority(int index) {
        Task temp = tasks.get(index);
        temp.markAsMediumPriority();
        tasks.set(index, temp);
    }

    /**
     * Marks a task in the Task List with low priority
     *
     * @param index the index of task to be marked
     */
    public void markAsLowPriority(int index) {
        Task temp = tasks.get(index);
        temp.markAsLowPriority();
        tasks.set(index, temp);
    }

    /**
     * Deletes a task in the Task List.
     *
     * @param index the index of task to be deleted
     */
    public void delete(int index) {
        tasks.remove(index);
    }
}
