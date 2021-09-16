package duke;

import java.util.List;

/**
 * TaskList class that is used by duke class to deal with the list of task.
 * It allows adding and deleting of tasks from the list.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds task to the list.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes the task from the list.
     *
     * @param taskIndex Index of the task to delete.
     */
    public void deleteTask(int taskIndex) {
        taskList.remove(taskIndex);
    }

    /**
     * Gets the length of the list.
     *
     * @return Length of list.
     */
    public int length() {
        return taskList.size();
    }

    /**
     * Gets the task list as a list.
     *
     * @return List of tasks.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Gets the task at the specific index of the list.
     *
     * @param taskIndex Index of the task.
     * @return Task at the index.
     */
    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }
}


