package dino.data;

import java.util.ArrayList;

import dino.task.Task;

/**
 * Represents the list of tasks that the user writes to.
 */
public class TaskList {

    protected ArrayList<Task> taskList;

    /**
     * Constructor for the TaskList object.
     *
     * @param taskList an ArrayList of tasks representing the saved task from a previous program execution
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Default constructor for the TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the taskList.
     *
     * @return the number of tasks in the taskList
     */
    public int getLength() {
        return this.taskList.size();
    }

    /**
     * Gets a specific task from the taskList.
     *
     * @param index The index of the item in the taskList (0-indexed)
     * @return The task at [index] position
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Removes a specific task from the taskList.
     *
     * @param task The task object in the taskList
     */
    public void removeTask(Task task) {
        this.taskList.remove(this.taskList.indexOf(task));
    }

    /**
     * Adds a task to the taskList.
     *
     * @param task The task to be added to the taskList
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Finds the task that contains the keyword.
     *
     * @param keyword the word to be included in the task description
     * @return The string representing the matching task
     */
    public String findTask(String keyword) {
        StringBuilder output = new StringBuilder();
        for (int i = 0, j = 1; i < this.getLength(); i++) {
            if (this.getTask(i).hasKeyword(keyword)) {
                output.append(j++).append(". ").append(this.getTask(i).toString()).append("\n");
            }
        }
        return output.toString();
    }

}
