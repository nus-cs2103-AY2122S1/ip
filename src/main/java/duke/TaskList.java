package duke;

import java.util.ArrayList;

/**
 * A class representing a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * A constructor to create an empty list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * A constructor to create a list of tasks with the given data.
     */
    public TaskList(ArrayList<Task> taskData) {
        tasks = taskData;
    }

    /**
     * Adds a task to the current list.
     * 
     * @param newTask The task to be added.
     * @return The task added.
     */
    public Task addTask(Task newTask) {
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Gets the task numbered <code>index</code>.
     * 
     * @param index The number of which the task should be retrieved from.
     * @return The task numbered <code>index</code>.
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Removes the task numbered <code>index</code>.
     * 
     * @param index The number of which the task should be removed.
     * @return The task removed.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index - 1);
    }

    /**
     * Returns the number of tasks in the list.
     * 
     * @return The number of tasks in the list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns the string representation of the task list.
     * 
     * @return The string representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder tempBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            tempBuilder.append(String.format("%d.%s\n", i + 1, tasks.get(i)));
        }
        return tempBuilder.toString();
    }

    /**
     * Returns the backup of format of the task list.
     * 
     * @return The backup of format of the task list.
     */
    public ArrayList<String> toBackupFormat() {
        ArrayList<String> backupData = new ArrayList<>();
        for (Task t: tasks) {
            backupData.add(t.toBackupFormat());
        }
        return backupData;
    }
}
