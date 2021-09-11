package duke.task;

import java.util.ArrayList;

import duke.exception.TaskAlreadyDoneException;
import duke.exception.TaskIndexOutOfBoundsException;

/**
 * This TaskList class implements a task list.
 */
public class TaskList {

    private ArrayList<Task> taskList;

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
        assert specifiedTask.isDone() : "The task should be correctly marked as done";
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
        return taskList.remove(taskId);
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
     * Tags a specified task in the list.
     *
     * @param taskId The index of the task to be tagged.
     * @param tagName The name of the tag given to the task.
     * @return The task that was tagged.
     * @throws TaskIndexOutOfBoundsException If the task index is illegal.
     */
    public Task tagTask(int taskId, String tagName) throws TaskIndexOutOfBoundsException {
        if (taskId < 0 || (taskId + 1) > taskList.size()) {
            throw new TaskIndexOutOfBoundsException();
        }
        Task taskToBeTagged = taskList.get(taskId);
        taskToBeTagged.setTag(tagName);
        return taskToBeTagged;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getNumberOfTasks() {
        return taskList.size();
    }

    /**
     * Finds all the tasks with the matching keyword.
     *
     * @param keyword The term to search for.
     * @return A TaskList with matching tasks.
     */
    public TaskList findTasksWithKeyword(String keyword) {
        ArrayList<Task> tasksWithMatchingKeyword = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                tasksWithMatchingKeyword.add(task);
            }
        }
        return new TaskList(tasksWithMatchingKeyword);
    }

    /**
     * Returns the string representation of a TaskList instance.
     *
     * @return A string representing a TaskList instance.
     */
    @Override
    public String toString() {
        String tasksInOrder = "";
        for (int i = 0; i < taskList.size(); i++) {
            tasksInOrder += (i + 1) + "." + taskList.get(i);
            if (i != taskList.size() - 1) {
                tasksInOrder += "\n";
            }
        }
        return tasksInOrder;
    }
}
