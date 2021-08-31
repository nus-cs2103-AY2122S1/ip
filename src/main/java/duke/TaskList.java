package duke;

import java.util.ArrayList;

/**
 * Wraps around an ArrayList of tasks.
 */
public class TaskList implements Cloneable {
    private ArrayList<Task> tasks;

    /**
     * Creates empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initializes tasklist with an ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList clone() {
        ArrayList<Task> clonedTasks = new ArrayList<>();
        for (Task task: tasks) {
            clonedTasks.add(task.clone());
        }
        return new TaskList(clonedTasks);
    }

    /**
     * Note: other becomes unusable after this function.
     *
     * @param other Source to move tasks from.
     */
    public void moveTasks(TaskList other) {
        tasks = other.tasks;
    }

    /**
     * Adds a task to TaskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks an element of Tasklist as done.
     *
     * @param i index of task to be MarkedAsDone, 0 indexed.
     * @return task that has been modified.
     * @throws DukeException If index out of range.
     */
    public Task markTaskAsDone(int i) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new DukeException("markTaskAsDone index out of range.");
        }
        Task task = tasks.get(i);
        task.markAsDone();
        return task;
    }

    /**
     * Deletes an element of Tasklist.
     *
     * @param i index of task to be removed, 0 indexed.
     * @return task that has been removed.
     * @throws DukeException If index out of range.
     */
    public Task removeTask(int i) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new DukeException("removeTask index out of range.");
        }
        Task task = tasks.get(i);
        tasks.remove(i);
        return task;
    }

    /**
     * Searches for tasks that matches keyword.
     *
     * @param key keyword used for searching tasks.
     * @return ArrayList of matching tasks.
     */
    public ArrayList<Task> findTasks(String key) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(key)) {
                result.add(task);
            }
        }
        return result;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }
}
