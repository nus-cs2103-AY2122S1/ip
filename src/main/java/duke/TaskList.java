package duke;

import java.util.ArrayList;

/**
 * List of current tasks and methods supporting list operations.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Initial capacity of 100 (Given default number)
     */
    public TaskList() {
        taskList = new ArrayList<>(100);
    }

    /**
     * Adds task to the list.
     *
     * @param task
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes task at index from the list.
     *
     * @param index
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Marks the task at index as done.
     *
     * @param index
     */
    public void markAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    /**
     * Retrieves the size of task list.
     *
     * @return int Size of task list.
     */
    public int getListSize() {
        return taskList.size();
    }

    /**
     * Gets the string representation of task at index.
     *
     * @param index
     * @return String toString() of task at index.
     */
    public String getTaskString(int index) {
        return taskList.get(index).toString();
    }

    /**
     * Exposes the task list for operations.
     *
     * @return ArrayList<Task> Duke's task list.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
