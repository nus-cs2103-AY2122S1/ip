package banana;

import java.util.List;
import java.util.ArrayList;

/**
 * The TaskList class stores
 * all the tasks.
 *
 * @author: Ravi Ananya
 */
public class TaskList {

    private ArrayList<Task> allTasks;

    /**
     * Constructor for the TaskList class.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.allTasks = tasks;
    }

    private ArrayList<Task> getTasks() {
        return allTasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param t the task to be added.
     */
    public void addTask(Task t) {
        allTasks.add(t);
    }

    /**
     * Removes a task from the list.
     *
     * @param t the task to be removed.
     */
    public void removeTask(Task t) {
        allTasks.remove(t);
    }

    /**
     * Retrives a task from the list.
     *
     * @param index the index where the task is located.
     * @return the task.
     */
    public Task getTask(int index) {
        assert index >= 0;
        return allTasks.get(index);
    }

    /**
     * Gets the size of the list.
     *
     * @return the size of the list.
     */
    public int getSize() {
        return allTasks.size();
    }

    /**
     * Appends tasks from the other TaskList.
     *
     * @param otherTasks the other TaskList.
     */
    public void addTasks(TaskList otherTasks) {
        allTasks.addAll(otherTasks.getTasks());
    }

    /**
     * Removes common tasks in the original
     * TaskList and the TaskList param.
     *
     * @param otherTasks the other TaskList.
     */
    public void removeTasks(TaskList otherTasks) {
        allTasks.removeAll(otherTasks.getTasks());
    }

    /**
     * Gets part of the TaskList, from
     * index start to end - 1.
     *
     * @param start the starting index (inclusive).
     * @param end   the end index (exclusive).
     */
    public TaskList subList(int start, int end) {
        List<Task> tempTasks = allTasks.subList(start, end);
        ArrayList<Task> newTasks = new ArrayList<>(tempTasks);
        return new TaskList(newTasks);
    }

}
