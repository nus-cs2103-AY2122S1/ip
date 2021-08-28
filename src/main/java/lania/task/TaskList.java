package lania.task;

import java.util.ArrayList;

/**
 * Represents the user's list of tasks.
 */
public class TaskList {

    /** ArrayList collection used to contain task list */
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList. Initialises an empty
     * ArrayList of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void update(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task in the task list as done
     * with the given index.
     *
     * @param i The index of the task to be completed.
     */
    public void complete(int i) {
        i--;
        tasks.get(i).markAsDone();
    }

    /**
     * Removes a task from the task list.
     *
     * @param i Index of the task to be removed.
     * @return The removed task.
     */
    public Task remove(int i) {
        i--;
        Task task = tasks.get(i);
        tasks.remove(i);
        return task;
    }

    /**
     * Gets the size of the task list.
     *
     * @return The size of task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the task specified by the index.
     *
     * @param i Index of task.
     * @return The task at index i.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Creates a new TaskList consisting only of tasks
     * that matches the keyword by looping through the
     * current list.
     *
     * @param s The keyword to match.
     * @return The new task list with only matching tasks.
     */
    public TaskList find(String s) {
        TaskList tempTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(s)) {
                tempTasks.update(tasks.get(i));
            }
        }
        return tempTasks;
    }
}
