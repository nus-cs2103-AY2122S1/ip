package duke.tasks;

import java.util.ArrayList;

/**
 * Represent the list of tasks in the task manager.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Instantiates an object of the TaskList class.
     *
     * @param tasks ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Integer getNumberOfTasks() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to the existing list of tasks.
     *
     * @param task Task to be added to the list of tasks.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(Integer taskNumber) {
        return this.tasks.get(taskNumber);
    }
}
