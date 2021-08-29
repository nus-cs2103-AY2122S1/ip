package duke;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Class that contains the list of tasks
 *
 */
public class TaskList {

    /** ArrayList of tasks */
    private ArrayList<Task> taskList;

    /** Counter to track tasklist size */
    private int counter = 0;

    /**
     * Constructor to initialize tasklist
     *
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Method to add task to tasklist
     *
     * @param task The task to be added to tasklist
     */
    public void add(Task task) {
        this.taskList.add(task);
        this.counter++;
    }

    /**
     * Method to delete task from tasklist
     *
     * @param index The index of the task in the tasklist
     * @return Deleted task
     */
    public Task delete(int index) {
        Task deletedTask = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        this.counter--;
        return deletedTask;
    }

    /**
     * Method to get task from tasklist given the index
     *
     * @param index The index of the task in tasklist
     * @return The required task
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Method to get size of tasklist
     *
     * @return Integer size of the tasklist
     */
    public int size() {
        return this.counter;
    }

    /**
     * Method to get the tasklist
     *
     * @return The tasklist
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Method to set specified task as done
     *
     * @param index The index of the task to be set as done in tasklist
     * @return The task set as done
     */
    public Task setDone(int index) {
        this.taskList.get(index - 1).setDone();
        return this.taskList.get(index - 1);
    }

    /**
     * String representation of the tasklist
     *
     * @return String representation of the tasklist
     */
    @Override
    public String toString() {
        String tasks = "";
        for (int i = 0; i < counter; i++) {
            tasks += (i + 1) + ". " + this.taskList.get(i) + "\n";
        }
        return tasks;
    }
}