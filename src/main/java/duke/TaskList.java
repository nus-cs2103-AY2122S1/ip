package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private Storage storage;

    /**
     * Constructor for TaskList class.
     * @param storage
     * @throws FileNotFoundException
     * @throws IOException
     * @throws DukeException
     */
    public TaskList(Storage storage) throws DukeException {
        this.storage = storage;
        this.tasks = this.storage.getTasksFromFile();
    }

    /**
     * Append Task to the list of tasks, and saves file to storage.
     * @param task Task to be added.
     * @throws DukeException If there is a generic Duke Exception.
     */
    public void add(Task task) throws DukeException {
        this.tasks.add(task);
        this.storage.saveToFile(tasks);
    }

    public Task get(int idx) {
        return this.tasks.get(idx);
    }

    public int size() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.size() == 0;
    }

    /**
     * Sets a task as done for a given index.
     * @param idx Index of task to be marked as done.
     */
    public void setDone(int idx) throws DukeException {
        tasks.get(idx).setDone(true);
        storage.saveToFile(tasks);
    }

    /**
     * Remove a task for a given index.
     * @param idx Index of task to be removed.
     * @throws DukeException If there is a generic Duke Exception.
     */
    public void remove(int idx) throws DukeException {
        this.tasks.remove(idx);
        this.storage.saveToFile(tasks);
    }
}
