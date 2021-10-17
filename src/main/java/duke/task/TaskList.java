package duke.task;

import java.util.ArrayList;

import duke.storage.Storage;


public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructor for taskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added to the task list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param taskNum the index of the task (1-indexed).
     * @param storage the storage which holds all the user's data.
     * @return string that confirms the removal of the task.
     */
    public Task removeTask(int taskNum, Storage storage) {
        Task task = getTask(taskNum);
        this.taskList.remove(task);
        storage.save(this.taskList);
        return task;
    }

    /**
     * Marks task indicated by the task number as done.
     *
     * @param taskNum the index of the task (1-indexed).
     * @param storage the storage which holds all the user's data.
     * @return string that confirms the task has been marked done.
     */
    public Task markTaskAsDone(int taskNum, Storage storage) {
        Task task = getTask(taskNum);
        assert task != null;
        task.markAsDone();
        storage.save(this.taskList);
        return task;
    }

    /**
     * Returns the task indicated by the task number.
     *
     * @param taskNum the index of the task (1-indexed).
     * @return the task indicated by the task number.
     */
    public Task getTask(int taskNum) {
        return this.taskList.get(taskNum - 1);
    }

    /**
     * Returns a TaskList containing all the tasks that contain the search query.
     *
     * @param query the user's search query.
     * @return a tasklist that contains all the tasks that contain the search query.
     */
    public TaskList findTasks(String query) {
        TaskList matchingTasks = new TaskList();
        for (Task t : this.taskList) {
            if (t.getDescription().toLowerCase().contains(query)) {
                matchingTasks.addTask(t);
            }
        }
        return matchingTasks;
    }

    /**
     * Updates task given the task number and update, then updates file.
     *
     * @param taskNum the index of the task (1-indexed).
     * @param update the update to be applied to the task.
     * @param storage the storage which holds all the user's data.
     */
    public Task updateTask(int taskNum, String update, Storage storage) {
        Task task = getTask(taskNum);
        task = task.update(update);
        this.taskList.set(taskNum - 1, task);
        assert this.taskList != null;
        storage.save(this.taskList);
        return task;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the number of tasks in the task list.
     */
    public int getNumTasks() {
        return taskList.size();
    }
}
