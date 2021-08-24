package lania.task;

import java.util.ArrayList;

/**
 * Represents the user's list of tasks.
 */
public class TaskList {

    /** ArrayList collection used to contain task list */
    private ArrayList<Task> taskArrayList;

    /**
     * Constructor for TaskList. Initialises an empty
     * ArrayList of tasks.
     */
    public TaskList() {
        this.taskArrayList = new ArrayList<Task>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to be added.
     */
    public void update(Task t) {
        taskArrayList.add(t);
    }

    /**
     * Marks a task in the task list as done
     * with the given index.
     *
     * @param i Index of the task to be completed.
     */
    public void complete(int i) {
        i--;
        taskArrayList.get(i).markAsDone();
    }

    /**
     * Removes a task from the task list.
     *
     * @param i Index of the task to be removed.
     * @return The removed task.
     */
    public Task remove(int i) {
        i--;
        Task t = taskArrayList.get(i);
        taskArrayList.remove(i);
        return t;
    }

    /**
     * Gets the size of the task list.
     *
     * @return Size of task list.
     */
    public int size() {
        return taskArrayList.size();
    }

    /**
     * Gets the task specified by the index.
     *
     * @param i Index of task.
     * @return Task at index i.
     */
    public Task get(int i) {
        return taskArrayList.get(i);
    }
}
