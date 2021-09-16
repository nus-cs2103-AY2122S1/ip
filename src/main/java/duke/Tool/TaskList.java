package duke.Tool;

import duke.Tasks.Task;

import java.util.ArrayList;

/**
 * Represents TaskList class: contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Constructs TaskList class
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs TaskList class with input ArrayList
     *
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task in tasks list
     *
     * @param t
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Removes the task from the tasks list
     *
     * @param i
     * @return Task obeject
     */
    public Task remove(int i) {
        return tasks.remove(i);
    }

    /**
     * Marks down the tasks
     *
     * @param i
     */
    public void markDone(int i) {
        tasks.get(i).markDone();
    }

    /**
     * Gets the tasks from given index i
     *
     * @param i
     * @return Task object
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Gets the size of tasks list
     *
     * @return
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the tasks list
     *
     * @return Arraylist
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}