package Duke.Tool;

import Duke.Tasks.Task;

import java.util.ArrayList;

/**
 * Represents TaskList class: contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    private static ArrayList<Task> task;

    /**
     * The constructor for no input
     */
    public TaskList() {
        this.task = new ArrayList<Task>();
    }

    /**
     * The constructor with input ArrayList
     * @param task
     */
    public TaskList(ArrayList<Task> task) {
        this.task = task;
    }

    /**
     * The method of add task
     * @param t
     */
    public void add(Task t) {
        task.add(t);
    }

    /**
     * The method of remove task
     * @param i
     * @return Task obeject
     */
    public Task remove(int i) {
        return task.remove(i);
    }

    /**
     * The method of markDone
     * @param i
     */
    public void markDone(int i) {
        task.get(i).markDone();
    }

    /**
     * The method of get
     * @param i
     * @return Task object
     */
    public Task get(int i) {
        return task.get(i);
    }

    /**
     * The method of size
     * @return
     */
    public int size() {
        return task.size();
    }

    /**
     * The method of getTask
     * @return Arraylist
     */
    public ArrayList<Task> getTasks() {
        return this.task;
    }
}