package duke.classes;

import duke.tasks.Task;

import java.util.List;

public class TaskList {
    private List<Task> taskList;

    /**
     * Class Constructor
     * @param taskList List of Tasks to be manipulated and saved in the object
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the List of Tasks
     * @return Returns List of Tasks
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a Task to the List of Tasks
     * @param task Task to be added into the List
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * //Can be optimized / cleaned @usage
     * Removes a task at a specific index and returns it
     * @param index Index of the task to be removed
     * @return Returns the removed task
     */
    public Task remove(int index) {
        return taskList.remove(index);
    }

    /**
     * Retrieves the task at a specific index and returns it. Does not modify the list
     * @param index Index of the task to be returned
     * @return Returns the task at the index
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Gets the size of the List
     * @return Returns the size of the List of Tasks
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets the index of the last task in the list
     * @return Returns the index of the last task in the list
     */
    public int last() {
        return taskList.size() - 1;
    }

    /**
     * Checks if the list of tasks if empty
     * @return Returns boolean to indicate if the List isEmpty
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Completes the task at the specified index
     * @param index Index of the task to complete
     */
    public void completeTask(int index) {
        taskList.get(index).markAsDone();
    }
}
