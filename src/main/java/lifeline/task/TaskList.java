package lifeline.task;

import java.util.ArrayList;

import lifeline.exception.LifelineException;

/**
 * The class TaskList encapsulates a list of tasks using an ArrayList of tasks
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Default constructor for a TaskList
     *
     * @param taskList List of tasks to be stored
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to TaskList
     *
     * @param task Task to be added
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Returns task based on given index
     *
     * @param index Index of task to be returned
     * @return Task at specified index
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Returns number of tasks in TaskList
     *
     * @return Number of tasks in TaskList
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Deletes a task in TaskList at specified index
     *
     * @param index Index of task to be deleted
     * @return Deleted task
     */
    public Task deleteTask(int index) {
        Task taskToDelete = taskList.get(index);
        taskList.remove(index);
        return taskToDelete;
    }

    /**
     * Completes task in the TaskList at the specified index
     *
     * @param index Index of task to be completed
     * @return Completed task
     * @throws LifelineException If task is already completed
     */
    public Task completeTask(int index) throws LifelineException {
        Task taskToMarkAsComplete = taskList.get(index);
        if (taskToMarkAsComplete.isDone()) {
            throw new LifelineException("Task is already done!");
        }
        taskToMarkAsComplete.setDone(true);
        return taskToMarkAsComplete;
    }

    /**
     * Returns list of tasks
     *
     * @return ArrayList containing tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
