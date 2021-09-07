package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a list of Tasks.
 *
 * @author Joshua Yong
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Class constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Overloaded class constructor which initializes the TaskList with existing tasks.
     *
     * @param tasks An ArrayList of tasks to be stored in the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Sets the specified task from the TaskList as done.
     *
     * @param i Index of the Task to be set as done.
     */
    public void setTaskAsDone(int i) {
        tasks.get(i).setDone();
    }

    /**
     * Removes the specified task from the TaskList.
     *
     * @param i Index of the Task to be removed.
     */
    public void deleteTask(int i) {
        tasks.remove(i);
    }

    /**
     * Adds the given task to the TaskList.
     *
     * @param task The Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a formatted String of all tasks in the TaskList.
     * The String contains all Tasks in the TaskList with their task number.
     *
     * @return A formatted String representation of the TaskList.
     */
    public String getAllTasksString() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:");
        int taskCount = tasks.size();
        for (int i = 0; i < taskCount; i++) {
            result.append("\n" + (i + 1) + ". " + tasks.get(i).toString());
        }
        return result.toString();
    }

    /**
     * Returns a formatted string of all tasks in the TaskList matching a given keyword.
     * The String contains all Tasks in the TaskList with numbering.
     *
     * @param keyword The keyword which is filtered for.
     * @return A formatted String representation of the matching Tasks in the TaskList.
     */
    public String getMatchingTasksString(String keyword) {
        ArrayList<Task> matchingTaskList = new ArrayList<>(tasks);
        matchingTaskList.removeIf(task -> !task.getDescription().contains(keyword));
        StringBuilder result = new StringBuilder("Here are the tasks in your list:");
        int taskCount = matchingTaskList.size();
        for (int i = 0; i < taskCount; i++) {
            result.append("\n" + (i + 1) + ". " + matchingTaskList.get(i).toString());
        }
        return result.toString();
    }

}
