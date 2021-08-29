package duke.storage;

import java.io.IOException;
import java.util.ArrayList;

import duke.DukeException;
import duke.task.Task;

/**
 * Represents a list of tasks
 */
public class TaskList {
    private static TaskList instance = null;

    private ArrayList<Task> tasks;
    private TaskStorage storage;

    private TaskList() throws DukeException {
        try {
            this.storage = new TaskStorage();
            this.tasks = storage.readTasksFromMemory();
        } catch (IOException e) {
            throw new DukeException("Cannot read tasks from memory!");
        }
    }

    /**
     * Gets the instance <code>TaskList</code>
     *
     * @return the instance of the class
     * @throws DukeException if cannot read tasks from memory
     */
    public static TaskList getInstance() throws DukeException {
        if (instance == null) {
            instance = new TaskList();
            return instance;
        }
        return instance;
    }

    /**
     * Gets all tasks
     *
     * @return array list of all tasks
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Gets a specific task in the task list
     *
     * @param index index of the task
     * @return the task at index <code>index</code>
     * @throws IndexOutOfBoundsException if index is outside the range of the list
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in the list
     *
     * @return the number of tasks in the list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the list
     *
     * @param task a task to add to the list
     * @return true if adding successfully, else false
     * @throws IOException if cannot write the task to memory
     */
    public boolean add(Task task) throws IOException {
        boolean isAdded = tasks.add(task);
        storage.writeToMem(this.tasks);
        return isAdded;
    }

    /**
     * Marks a task as done
     *
     * @param index the index of the task
     * @throws IOException if cannot edit the task in memory
     */
    public void markDone(int index) throws IOException {
        this.tasks.get(index).setDone(true);
        storage.writeToMem(this.tasks);
    }

    /**
     * Find tasks based on a keyword
     * @param keyword keyword to find tasks
     * @return array list of matching tasks
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> resultTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getContent().contains(keyword)) {
                resultTasks.add(task);
            }
        }
        return resultTasks;
    }

    /**
     * Deletes a task from the list
     *
     * @param index the index of the task
     * @return the deleted task
     * @throws IndexOutOfBoundsException if <code>index</code> is not in the range of the list
     * @throws IOException id cannot delete the task from memory
     */
    public Task delete(int index) throws IndexOutOfBoundsException, IOException {
        Task deletedTask = tasks.remove(index);
        storage.writeToMem(this.tasks);
        return deletedTask;
    }
}
