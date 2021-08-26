package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Contains the task list
 */
public class TaskList {
    ArrayList<Task> taskList;

    /**
     * Class Constructor
     *
     * @param taskList the task list
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the size of the list
     *
     * @return the number of tasks
     */
    public int size() {
        return taskList.size();
    }

    public Task get(int taskNo) {
        return this.taskList.get(taskNo - 1);
    }

    /**
     * Checks if there are any tasks in the list
     *
     * @return whether the list is empty
     */
    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    /**
     * Removes a task from the list
     *
     * @param taskNo the specified task number
     */
    public void remove(int taskNo) {
        this.taskList.remove(taskNo - 1);
    }

    /**
     * Adds a task to the list
     *
     * @param task the task to add
     */
    public void add(Task task) {
        this.taskList.add(task);
    }
}
