package duke;

import duke.tasks.Task;
import java.util.ArrayList;

/**
 * Encapsulates a List of Tasks and supports various functions related to managing the List of Tasks.
 */
public class TaskList {
    protected final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds task to the List.
     *
     * @param task The task to be added to the List.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Gets the task at given index.
     *
     * @param idx Index of task to get.
     * @return Task at the given index.
     */
    public Task getTask(int idx) {
        return taskList.get(idx);
    }

    /**
     * Deletes task at given index.
     *
     * @param idx Index of task to be deleted.
     */
    public void removeTask(int idx) {
        taskList.remove(idx);
    }

    /**
     * Returns all Tasks in the TaskList that match a given keyword.
     * @param keyword The given keyword.
     * @return A TaskList of all Tasks that match the given keyword.
     */
    public TaskList findMatchingTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns size of the task list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    @Override
    public String toString() {
        String lst = "";
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            lst += "\n" + i + ". " + task;
        }
        return lst;
    }

}
