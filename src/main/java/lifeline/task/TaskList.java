package lifeline.task;

import static lifeline.util.ErrorString.ERROR_NO_TASKS_FOUND;
import static lifeline.util.ErrorString.ERROR_TASK_ALREADY_DONE;

import java.util.ArrayList;
import java.util.Collections;

import lifeline.exception.LifelineException;

/**
 * The class TaskList encapsulates a list of tasks using an ArrayList of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Default constructor for a TaskList.
     *
     * @param taskList List of tasks to be stored.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to TaskList.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Returns task based on given index.
     *
     * @param index Index of task to be returned.
     * @return Task at specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < taskList.size();
        return taskList.get(index);
    }

    /**
     * Returns number of tasks in TaskList.
     *
     * @return Number of tasks in TaskList.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Deletes a task in TaskList at specified index.
     *
     * @param index Index of task to be deleted.
     * @return Deleted task.
     */
    public Task deleteTask(int index) {
        assert index >= 0 && index < taskList.size();
        Task taskToDelete = taskList.get(index);
        taskList.remove(index);
        return taskToDelete;
    }

    /**
     * Completes task in the TaskList at the specified index.
     *
     * @param index Index of task to be completed.
     * @return Completed task.
     * @throws LifelineException If task is already completed.
     */
    public Task completeTask(int index) throws LifelineException {
        assert index >= 0 && index < taskList.size();
        Task taskToMarkAsComplete = taskList.get(index);
        if (taskToMarkAsComplete.isDone()) {
            throw new LifelineException(ERROR_TASK_ALREADY_DONE);
        }
        taskToMarkAsComplete.setDone(true);
        return taskToMarkAsComplete;
    }

    /**
     * Returns list of tasks containing keyword.
     *
     * @param keyword Keyword used to search for tasks.
     * @return List of tasks containing keyword.
     * @throws LifelineException If no tasks with keyword is found.
     */
    public TaskList findTasks(String keyword) throws LifelineException {
        TaskList foundTasks = new TaskList(new ArrayList<>());
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            if (currTask.getName().toLowerCase().contains(keyword)) {
                foundTasks.add(currTask);
            }
        }
        if (foundTasks.getSize() == 0) {
            throw new LifelineException(ERROR_NO_TASKS_FOUND);
        }
        return foundTasks;
    }

    /**
     * Returns list of tasks.
     *
     * @return ArrayList containing tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Deletes multiple tasks in TaskList at specified indices.
     *
     * @param taskIndices Indices to delete.
     */
    public void deleteMultipleTasks(ArrayList<Integer> taskIndices) {
        Collections.sort(taskIndices);
        for (int i = taskIndices.size() - 1; i >= 0; i--) {
            this.deleteTask(taskIndices.get(i));
        }
    }

    /**
     * Completes multiple tasks in TaskList at specified indices.
     *
     * @param taskIndices Indices to complete.
     * @throws LifelineException if task is already completed.
     */
    public void completeMultipleTasks(ArrayList<Integer> taskIndices) throws LifelineException {
        for (int i = taskIndices.size() - 1; i >= 0; i--) {
            this.completeTask(taskIndices.get(i));
        }
    }

    /**
     * Returns number of uncompleted tasks.
     *
     * @return Number of uncompleted tasks.
     */
    public int getNumberOfUncompletedTasks() {
        int uncompletedTasks = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (!taskList.get(i).isDone()) {
                uncompletedTasks++;
            }
        }
        return uncompletedTasks;
    }
}
