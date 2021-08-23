package duke.task;

import duke.exception.TaskAlreadyDoneException;
import duke.exception.TaskIndexOutOfBoundsException;

import java.util.ArrayList;

/**
 * This TaskList class implements a task list.
 */
public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructor for a TaskList instance that takes in a list of tasks.
     *
     * @param taskList An ArrayList containing the Task instances.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the current list of tasks.
     *
     * @return An ArrayList containing the current tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskId The id of the task to be marked as done.
     * @return The task that was marked as done.
     * @throws TaskIndexOutOfBoundsException If the task index is illegal.
     * @throws TaskAlreadyDoneException If the task has previously been marked as done.
     */
    public Task markAsDone(int taskId) throws TaskIndexOutOfBoundsException, TaskAlreadyDoneException {
        if (taskId < 0 || (taskId + 1) > taskList.size()) {
            throw new TaskIndexOutOfBoundsException();
        }
        Task specifiedTask = taskList.get(taskId);
        if (specifiedTask.getStatusIcon().equals("X")) {
            throw new TaskAlreadyDoneException();
        }
        specifiedTask.markAsDone();
        return specifiedTask;
    }

    /**
     * Deletes the specified task from the list.
     *
     * @param taskId The index of the task to be deleted.
     * @return The task that was deleted.
     * @throws TaskIndexOutOfBoundsException If the task index is illegal.
     */
    public Task deleteTask(int taskId) throws TaskIndexOutOfBoundsException {
        if (taskId < 0 || (taskId + 1) > taskList.size()) {
            throw new TaskIndexOutOfBoundsException();
        }
        Task removedTask = taskList.remove(taskId);
        return removedTask;
    }

    /**
     * Adds the specified task to the list.
     *
     * @param taskToBeAdded The task to be added.
     */
    public void addTask(Task taskToBeAdded) {
        taskList.add(taskToBeAdded);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getNumberOfTasks() {
        return taskList.size();
    }
}
