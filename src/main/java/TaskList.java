import java.util.List;
import java.io.IOException;
import java.io.FileNotFoundException;

public class TaskList {
    private List<Task> tasks;
    private Storage storage;

    public TaskList(Storage storage) throws FileNotFoundException, IOException, DukeException {
        this.storage = storage;
        this.tasks = this.storage.getTasksFromFile();
    }

    /**
     * @param task
     * @throws DukeException
     */
    public void add(Task task) throws DukeException {
        this.tasks.add(task);
        this.storage.saveToFile(tasks);
    }

    /**
     * @param idx
     * @return Task
     */
    public Task get(int idx) {
        return this.tasks.get(idx);
    }

    /**
     * @return int
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * @return boolean
     */
    public boolean isEmpty() {
        return this.tasks.size() == 0;
    }

    /**
     *
     * @param idx
     */
    public void setDone(int idx) throws DukeException {
        tasks.get(idx).setDone(true);
        storage.saveToFile(tasks);
    }

    /**
     * @param idx
     * @throws DukeException
     */
    public void remove(int idx) throws DukeException {
        this.tasks.remove(idx);
        this.storage.saveToFile(tasks);
    }
}