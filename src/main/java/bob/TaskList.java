package bob;

import bob.task.Task;

import java.util.ArrayList;

/**
 * Represents the user's list of tasks to be completed.
 */
public class TaskList {
    /** List of user tasks to keep track of. */
    private ArrayList<Task> taskList;

    /**
     * Constructor for a new empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for a new TaskList containing the provided tasks.
     *
     * @param tasks Array containing the tasks to be added to the new TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Adds the given task to the current list.
     *
     * @param task Task to be added to the current TaskList.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns the current list of tasks.
     *
     * @return Current list of tasks as a String.
     */
    public String getList() {
        String result = "";
        for (int index = 0; index < this.taskList.size(); index++) {
            result = result + (index + 1) + "." + this.taskList.get(index).printTask() + "\n";
        }
        return result;
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param index The index of the task in the list to be marked as completed.
     * @return String representation of the completed task.
     */
    public String markIndexCompleted(int index) {
        Task selectedTask = this.taskList.get(index);
        selectedTask.markCompleted();
        return selectedTask.printTask();
    }

    /**
     * Deletes the task at the specified index from the list.
     *
     * @param index The index of the task in the list to be deleted.
     * @return String representation of the deleted task.
     */
    public String deleteIndex(int index) {
        Task selectedTask = this.taskList.get(index);
        this.taskList.remove(index);
        return selectedTask.printTask();
    }

    /**
     * Returns the number of tasks in the list currently.
     *
     * @return The number of tasks in the list as a String.
     */

    public String getNoOfTasks() {
        return Integer.toString(this.taskList.size());
    }

    /**
     * Returns the task at the specified index from the list.
     *
     * @param index The index of the task in the list to be returned.
     * @return Specified task.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }
}
