package duke.tasklist;

import java.util.ArrayList;

import duke.exceptions.OutOfBoundException;
import duke.tasks.Task;

/**
 * Represents a TaskList that stores an array of Tasks.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * A constructor for TaskList specifying list of saved tasks.
     *
     * @param tasks Array list of Task.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Marks the task done and returns the task description.
     *
     * @param index Marks the task at index as done.
     * @return String representation of the task marked done.
     */
    public String done(int index) throws OutOfBoundException {
        return tasks.get(index).markDone();
    }

    /**
     * Deletes the task and returns the task description.
     *
     * @param index Deletes the task at index.
     * @return String representation of the task deleted.
     */
    public String delete(int index) throws OutOfBoundException {
        String deleteMsg = tasks.get(index).delete();
        tasks.remove(index);
        return deleteMsg;
    }

    /**
     * Returns the task description after successfully added.
     *
     * @param task Task to be added.
     * @return String representation of the added task.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return task.getTask();
    }

    /**
     * Returns the size of the current task list.
     *
     * @return Size of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the string of task at index.
     *
     * @return String representation of the task at index.
     */
    public String getTask(int index) {
        return tasks.get(index).getTask();
    }

    public Task get(int index) {
        return tasks.get(index);
    }
}
