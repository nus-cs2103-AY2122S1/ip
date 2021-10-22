package duke;

import java.util.ArrayList;

import duke.exceptions.InvalidDescriptionException;
import duke.task.Task;

/**
 * Contains the task list e.g. it has operation to add/delete tasks in the list.
 */
public class TaskList {

    private ArrayList<Task> arrayList;

    /**
     * Constructor for a new, empty TaskList.
     */
    public TaskList() {
        this.arrayList = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.arrayList.add(task);
    }

    /**
     * Returns a task at the specified index.
     *
     * @param index Index of task.
     * @return Task Task at index.
     * @throws InvalidDescriptionException If index is out of bounds of TaskList.
     */
    public Task getIndex(int index) throws InvalidDescriptionException {
        if (index < 1 || index > arrayList.size()) {
            throw new InvalidDescriptionException("The task you have indicated does not exist!");
        }
        return this.arrayList.get(index - 1);
    }

    /**
     * Marks a specific task as done.
     *
     * @param index Index of task.
     * @return Task Completed Task at index.
     * @throws InvalidDescriptionException
     */
    public Task markAsDone(int index) throws InvalidDescriptionException {
        Task completedTask = this.getIndex(index);
        completedTask.markAsDone();
        return completedTask;
    }

    /**
     * Deletes a specific task.
     *
     * @param index Index of task.
     * @return Task Deleted Task at index.
     * @throws InvalidDescriptionException
     */
    public Task deleteTask(int index) throws InvalidDescriptionException {
        Task deletedTask = this.getIndex(index);
        this.arrayList.remove(index - 1);
        return deletedTask;
    }

    /**
     * Searches the TaskList for tasks containing keyword.
     *
     * @param keyword Provided by user.
     * @return ArrayList Matched tasks.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task t : arrayList) {
            if (t.getDescription().contains(keyword)) {
                matchedTasks.add(t);
            }
        }
        return matchedTasks;
    }

    /**
     * Gets the entire instance of the TaskList.
     * TODO: Change this to toString (information hiding).
     * @return
     */
    public ArrayList<Task> getAll() {
        return this.arrayList;
    }
}
