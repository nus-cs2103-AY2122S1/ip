package duke;

import duke.tasks.Task;
import duke.utils.DukeException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final static DukeException ERROR_DB = new DukeException("Error loading database.");
    private List<Task> db = new ArrayList<>();
    private Storage storage;

    /**
     * Creates empty task list.
     * If loadFromStorage == true, initializes database and loads any stored tasks.
     *
     * @param loadFromStorage Inform the TaskList whether to load tasks from storage.
     * @throws DukeException
     */
    public TaskList(boolean loadFromStorage) throws DukeException {
        if (loadFromStorage) {
            this.storage = new Storage(this);
        }
    }

    /**
     * Adds a Task to the list.
     *
     * @param task to be added.
     * @throws DukeException
     */
    public void add(Task task) throws DukeException {
        db.add(task);
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
        Task t = db.get(index);
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
        Task t = db.get(index);
        db.remove(index);
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
        return db.contains(task);
    }

    /**
     * Returns the number of tasks.
     *
     * @return number of tasks.
     */
    public int size() {
        return db.size();
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
        db = new ArrayList<>();
        storage.purge();
    }

    /**
     * Getter for the enclosed List<Task>.
     *
     * @return The raw task list.
     */
    public List<Task> getList() {
        return db;
    }

    public TaskList find(String word) throws DukeException {
        TaskList filtered = new TaskList(false);
        filtered.db = db.stream().filter(x -> x.matchWord(word)).collect(Collectors.toList());
        return filtered;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (db.size() == 0) {
            return "You have no tasks!";
        }
        for (int i = 1; i <= db.size(); i++) {
            sb.append("\n\t ");
            sb.append(i + "." + db.get(i - 1));
        }
        sb.deleteCharAt(0);
        return sb.toString();
    }
}
