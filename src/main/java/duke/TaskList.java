package duke;

import java.util.ArrayList;

import duke.task.Task;

/** A class which encapsulates the list of
 * tasks stored.
 */
public class TaskList {

    /** An arraylist to store the tasks */
    private ArrayList<Task> taskList;

    /**
     * A public constructor for TaskList which
     * initializes the taskList to an empty Array
     * List.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * A public constructor which initializes the taskList
     * to the given one.
     *
     * @param taskList The list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {

        this.taskList = taskList;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the list given the index of the task.
     *
     * @param num The index of the task to be deleted.
     */
    public void delete(int num) {

        taskList.remove(num - 1);
    }

    /**
     * Returns the size of the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns the task at the given index.
     *
     * @param index The index number of the task to obtain.
     * @return The task stored at the given index.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

}
