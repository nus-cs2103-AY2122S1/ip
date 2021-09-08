package duke.data;

import java.util.ArrayList;

import duke.commands.Task;

/**
 * Encapsulates a list of tasks.
 * This class also contains methods that are
 * related to the operations on the list of tasks.
 *
 * @author Jason Ng
 * @version Duke Level-10
 */
public class TaskList {
    /** List of tasks */
    protected ArrayList<Task> taskList;

    /**
     * Constructor for a TaskList.
     *
     * @param taskList The array of tasks to be initialized with.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the taskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task from the taskList.
     * The index of the element is provided for deletion,
     * and the deleted task is returned.
     *
     * @param index The index of the element to be deleted.
     * @return The deleted task.
     */
    public Task delete(int index) {
        Task toDelete = taskList.get(index);
        this.taskList.remove(index);
        return toDelete;
    }

    /**
     * Marks task as done,
     * and returns string representation of task being marked done.
     * Task is marked based on index of task provided.
     *
     * @return string representation of event task being marked done.
     */
    public String markDone(int index) {
        return this.taskList.get(index).markDone();
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns the task in the taskList based on its index.
     *
     * @param index The index of the task to be returned.
     * @return The task.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the entire taskList.
     *
     * @return The entire taskList.
     */
    public ArrayList<Task> getEntire() {
        return this.taskList;
    }
}
