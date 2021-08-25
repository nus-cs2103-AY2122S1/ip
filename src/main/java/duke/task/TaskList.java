package duke.task;

import duke.DukeException;
import duke.logic.LStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Encapsulates a list of tasks that the user wants to keep track of.
 */
public class TaskList {
    private final List<Task> tasks;
    private final int limit;

    /**
     * Creates the task list with the given limit.
     *
     * @param limit The limit to the size of the list. The number of tasks cannot exceed this limit.
     */
    public TaskList(int limit) {
        tasks = new ArrayList<>();
        this.limit = limit;
    }

    /**
     * Adds the task into the list.
     *
     * @param task The task to be added
     * @return true if the task is successfully added, or false if the task is not added as the list is full.
     */
    public boolean addTask(Task task) {
        if (tasks.size() >= limit) {
            return false;
        }
        tasks.add(task);
        return true;
    }

    private boolean validTaskNumber(int taskNumber) {
        return taskNumber <= tasks.size() && taskNumber > 0;
    }

    /**
     * Removes the task according to the specified task number.
     *
     * @param taskNumber The task number of the task to be removed. This is the number that the user sees.
     * @return the task that is being removed
     * @throws InvalidTaskNumberException when the task number given is invalid.
     */
    public Task removeTask(int taskNumber) {
        if (validTaskNumber(taskNumber)) {
            return tasks.remove(taskNumber - 1);
        }
        throw new InvalidTaskNumberException();
    }

    /**
     * Gets the task associated with the task number.
     * @param taskNumber The task number of the task to be retrieved. This is the number that the user sees.
     * @return the relevant task
     * @throws InvalidTaskNumberException when the task number given is invalid.
     */
    public Task getTask(int taskNumber) {
        if (!validTaskNumber(taskNumber)) {
            throw new InvalidTaskNumberException();
        }
        return tasks.get(taskNumber - 1);
    }

    /**
     * Gets all the tasks in the list.
     *
     * @return the immutable list of tasks in this task list.
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * Marks the selected task as done.
     *
     * @param taskNumber the task number to be marked as done. This is the number that the user sees.
     * @return true if task is successfully marked, or false if task has been marked as done before.
     * @throws InvalidTaskNumberException when the task number given is invalid.
     */
    public boolean markAsDone(int taskNumber) {
        return getTask(taskNumber).markAsDone();
    }

    public int size() {
        return tasks.size();
    }

    private class InvalidTaskNumberException extends DukeException {
        public InvalidTaskNumberException() {
            super(tasks.size() > 1
                    ? "Please input a value between 1 and " + tasks.size()
                    : tasks.size() == 1
                    ? "You can only input the value 1"
                    : "There are no tasks so far");
        }
    }
}
