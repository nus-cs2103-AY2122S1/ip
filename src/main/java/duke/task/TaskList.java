package duke.task;

import java.util.ArrayList;

/**
 * A duke.task list to keep track of the tasks.
 */
public class TaskList {
    /**
     * ArrayList of tasks that contains all the tasks to be completed.
     */
    private ArrayList<Task> tasks;

    /**
     * Length of the tasks available.
     */
    private int length = 0;

    /**
     * Empty duke.task.TaskList constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * duke.task.TaskList constructor with tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.length = tasks.size();
    }

    /**
     * Method to check if a duke.task with the corresponding index is in the duke.task list.
     *
     * @param index Index of duke.task to be checked.
     * @return boolean Indicate whether the duke.task is in the duke.task list.
     */
    public boolean isValidTaskIndex(int index) {
        return index >= 0 && index < length;
    }

    /**
     * Method to return the specific duke.task asked for.
     *
     * @param index Index of the duke.task desired.
     * @return The duke.task corresponding to the index given.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Method to add a new duke.task to the duke.task list.
     *
     * @param newTask The duke.task to be added.
     * @return A new duke.task list that contains the required tasks.
     */
    public TaskList add(Task newTask) {
        ArrayList<Task> newList = new ArrayList<>(tasks);
        newList.add(newTask);
        return new TaskList(newList);
    }

    /**
     * Method to mark a specific duke.task as completed.
     *
     * @param index Index of the duke.task to be marked as completed.
     * @return The duke.task that was marked as completed.
     */
    public Task markTaskAsCompleted(int index) {
        Task task = tasks.get(index);
        task.markAsCompleted();
        return task;
    }

    /**
     * Method to delete duke.task from the duke.task list.
     *
     * @param index Index of the duke.task to be deleted.
     * @return A new duke.task list that contains the remaining tasks.
     */
    public TaskList deleteTask(int index) {
        ArrayList<Task> newList = new ArrayList<>(tasks);
        newList.remove(index);
        return new TaskList(newList);
    }

    /**
     * Gets the status of the current duke.task list.
     *
     * @return String representation of the number of tasks in the duke.task list.
     */
    public String status() {
        String t = this.length != 1 ? "tasks" : "duke/task";
        return
                String.format("Now you have %d %s in the list.",
                this.length, t);
    }

    /**
     * String representation of the duke.task list.
     * @return String representation of the respective tasks
     * in the duke.task list.
     */
    @Override
    public String toString() {
        String str = "";
        int i = 1;
        for (Task item : tasks) {
            str += String.format("%4s%d. %s\n", " ", i, item);
            i++;
        }
        return str;
    }
}
