package duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds task onto task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns the task specified by the index.
     *
     * @param index Index of the task.
     * @return Task.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of task list.
     */
    public Integer taskListSize() {
        return this.taskList.size() - 1;
    }

    /**
     * Sets the task specified as done.
     *
     * @param index Index of the task.
     */
    public void setTaskDone(int index) {
        taskList.get(index).markAsDone();
    }

    /**
     * Remove the task specified by the index.
     *
     * @param index Index of the task.
     * @return The task that is removed.
     */
    public Task removeTask(int index) {
        Task.noOfTask -= 1;
        return taskList.remove(index);
    }
}
