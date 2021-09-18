package duke;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Retrieves a task from the list.
     *
     * @param index The index of the task to be retrieved from the list.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return taskList.get(index - 1);
    }

    /**
     * Removes a task from the task list.
     *
     * @param index The index of the task in the list.
     */
    public void removeTask(int index) {
        taskList.remove(index - 1);
    }

}
