package duke;

import duke.tasks.Task;
import duke.utils.DukeException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList {
    private final static DukeException ERROR_DB = new DukeException("Error loading database.");
    private List<Task> database = new ArrayList<>();
    private Storage storage;

    /**
     * Creates empty task list at default location.
     * @param loadFromStorage Inform the TaskList whether to load tasks from storage.
     * @throws DukeException
     */
    public TaskList(boolean loadFromStorage) throws DukeException {
        if (loadFromStorage) {
            this.storage = new Storage(this);
        }
    }

    /**
     * Creates empty task list, to be stored at a particular location.
     * If loadFromStorage == true, initializes database and loads any stored tasks.
     * @param loadFromStorage
     * @param fileName
     * @throws DukeException
     */
    public TaskList(boolean loadFromStorage, String fileName) throws DukeException {
        if (loadFromStorage) {
            this.storage = new Storage(this, fileName);
        }
    }

    /**
     * Adds a Task to the list.
     *
     * @param task to be added.
     * @throws DukeException
     */
    public void add(Task task) throws DukeException {
        database.add(task);
        if (storage != null) {
            storage.update(task);
        }
    }

    /**
     * Marks the task at index as complete.
     *
     * @param index of task to be marked as complete.
     * @throws DukeException
     */
    public Task markAsDone(int index) throws DukeException {
        Task t = database.get(index);
        t.markComplete();
        if (storage != null) {
            storage.update(this);
        }
        return t;
    }

    /**
     * Deletes a task from the list.
     *
     * @param index of task to be deleted.
     * @return The deleted task.
     * @throws DukeException
     */
    public Task delete(int index) throws DukeException {
        Task t = database.get(index);
        database.remove(index);
        if (storage != null) {
            storage.update(this);
        }
        return t;
    }

    /**
     * Returns if the task is in this task list.
     *
     * @param task to be checked.
     * @return if task list contains task.
     */
    public boolean contains(Task task) {
        return database.contains(task);
    }

    /**
     * Returns the number of tasks.
     *
     * @return number of tasks.
     */
    public int size() {
        return database.size();
    }

    public void close() throws DukeException {
        storage.close();
    }

    /**
     * Clears the task list and the database.
     *
     * @throws DukeException
     */
    public void clear() throws DukeException {
        database = new ArrayList<>();
        storage.purge();
    }

    /**
     * Getter for the enclosed List<Task>.
     *
     * @return The raw task list.
     */
    public List<Task> getList() {
        return database;
    }

    /**
     * Finds the word in the TaskList.
     * @param word word to filter tasks by.
     * @return filtered TaskList.
     * @throws DukeException
     */
    public TaskList find(String word) throws DukeException {
        TaskList filtered = new TaskList(false);
        filtered.database = database.stream().filter(x -> x.matchWord(word)).collect(Collectors.toList());
        return filtered;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (database.size() == 0) {
            return "You have no tasks!";
        }
        
        IntStream.range(0, database.size())
                .forEach(x -> sb.append(String.format("\n%d. %s", x + 1, database.get(x))));
        return sb.substring(1);
    }
}
