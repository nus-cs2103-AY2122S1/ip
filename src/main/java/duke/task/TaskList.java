package duke.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 *
 * @author Gordon Yit
 * @version Cs2103T, Semester 2
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Class constructor, loads arraylist of tasks from the storage to the tasks arraylist.
     *
     * @param tasksStored an arraylist of tasks.
     */
    public TaskList(ArrayList<Task> tasksStored) {
        tasks = new ArrayList<>();
        this.tasks.addAll(tasksStored);
    }

    /**
     * Alternative class constructor, used when there is an error loading the file.
     * Initializes an empty arraylist.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a duke.task to the tasks list.
     *
     * @param task a duke.task to be added.
     * @return the added duke.task.
     */
    public Task add(Task task) {
        this.tasks.add(task);
        return task;
    }

    /**
     * Retrieves a duke.task based on the given index.
     *
     * @param taskIndex index of the duke.task.
     * @return a duke.task matching the given index.
     * @throws IndexOutOfBoundsException if taskIndex is negative or greater than size of tasks.
     */
    public Task getTask(int taskIndex) throws IndexOutOfBoundsException {
        return tasks.get(taskIndex);
    }

    /**
     * Marks the duke.task corresponding to the done.
     *
     * @param taskIndex index of the duke.task to be marked done.
     * @return the duke.task marked done.
     * @throws IndexOutOfBoundsException if taskIndex is negative or greater than size of tasks.
     */
    public Task markDone(int taskIndex) throws IndexOutOfBoundsException {
        Task task = getTask(taskIndex);
        assert task != null : "task cannot be null";
        task.markAsDone();
        return task;
    }

    /**
     * Deletes a duke.task from the duke.task list.
     *
     * @param taskIndex index of the duke.task to be deleted.
     * @return the deleted duke.task.
     * @throws IndexOutOfBoundsException if taskIndex is negative or more than number of tasks.
     */
    public Task delete(int taskIndex) throws IndexOutOfBoundsException {
        Task task = tasks.get(taskIndex);
        assert task != null : "task deleted must exist";
        tasks.remove(task);
        return task;
    }

    /**
     * Finds all the tasks matching the date given.
     *
     * @param searchPhrase used to filter out tasks.
     * @return an arraylist of tasks which fall on that date.
     */
    public TaskList findMatchingTasks(String searchPhrase) {
        TaskList matchingTasks = new TaskList();
        for (Task t : tasks) {
            if (t.contains(searchPhrase)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns number of tasks currently tracked by Duke.
     *
     * @return size of tasks arraylist.
     */
    public int getNumTasks() {
        int numTasks = tasks.size();
        assert numTasks >= 0 : "number of tasks cannot be negative";
        return numTasks;
    }
}
