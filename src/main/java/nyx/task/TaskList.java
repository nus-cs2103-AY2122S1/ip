package nyx.task;

import java.util.ArrayList;

/**
 * Represents a list to keep track of Task objects.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a tasks list containing tasks specified in the given list of tasks.
     *
     * @param tasks The list of tasks to be placed into this task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty tasks list.
     */
    public TaskList() {
        this(new ArrayList<Task>());
    }

    /**
     * Returns the task at the specified position in this list.
     *
     * @param index Index of the task to return.
     * @return the task at the specified position in this list.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks currently in this list.
     *
     * @return the number of tasks in this list.
     */
    public int getNumTasks() {
        return tasks.size();
    }

    /**
     * Appends the specified task to the end of this list.
     *
     * @param task The task to be appended to this list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified position in this list.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Marks the task at the specified position in this list as done.
     * Returns true if the task is successfully marked as done, false otherwise.
     *
     * @param index The index of the task to be marked as done.
     * @return true if the task is successfully marked as done, false otherwise.
     */
    public boolean markDone(int index) {
        if (tasks.isEmpty()) {
            return false;
        } else {
            tasks.get(index).setDone();
            return true;
        }
    }

    /**
     * Returns a combined String representation of all the tasks in the list
     * in the format required for saving into hard disk.
     *
     * @return String representation of all the tasks in the list in the format required for saving into hard disk.
     */
    public String getSaveFormat() {
        StringBuilder sb = new StringBuilder();
        tasks.stream().map(Task::formatData).forEach(sb::append);
        return sb.toString();
    }

    /**
     * Returns the tasks that match the specified keyword.
     *
     * @param keyword The keyword to match the tasks.
     * @return The array list containing all the matching tasks.
     */
    public ArrayList<Task> searchTask(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        tasks.stream().filter(task -> task.getContent().contains(keyword)).forEach(filteredTasks::add);
        return filteredTasks;
    }

    /**
     * Returns a String representation of the list.
     *
     * @return String representation of the list.
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "You do not have any tasks currently";
        } else {
            StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 1; i <= tasks.size(); i++) {
                output.append(String.format("%d. %s\n", i, tasks.get(i - 1)));
            }
            return output.toString();
        }
    }
}
