package blue;

import java.util.ArrayList;
import java.util.List;

import blue.task.Task;

/**
 * Stores the tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds the task into the list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        assert task != null : "Task should exists";
        tasks.add(task);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param i Specified index.
     * @return Task at specified index.
     */
    public Task get(int i) {
        return tasks.get(i - 1);
    }

    /**
     * Returns all tasks in a new copy of list.
     *
     * @return All tasks.
     */
    public List<Task> getAll() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns the task at the specified index and removes it from list.
     *
     * @param i Specified index.
     * @return Task at specified index.
     */
    public Task remove(int i) {
        return tasks.remove(i - 1);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }
}
