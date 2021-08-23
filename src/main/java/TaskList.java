import java.io.*;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private static TaskList instance = null;
    private TaskStorage storage;

    private TaskList() throws DukeException {
        try {
            this.storage = new TaskStorage();
            this.tasks = storage.readTasksFromMemory();
        } catch (IOException e) {
            throw new DukeException("Cannot read tasks from memory!");
        }
    }

    public static TaskList getInstance() throws DukeException {
        if (instance == null) {
            instance = new TaskList();
            return instance;
        }
        return instance;
    }

    public ArrayList<Task> getAll() {
        return tasks;
    }

    public Task get(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public boolean add(Task task) throws IOException {
        boolean isAdded = tasks.add(task);
        storage.writeToMem(this.tasks);
        return isAdded;
    }

    public void markDone(int index) throws IOException {
        this.tasks.get(index).setDone(true);
        storage.writeToMem(this.tasks);
    }

    public Task delete(int index) throws IndexOutOfBoundsException, IOException {
        Task deletedTask = tasks.remove(index);
        storage.writeToMem(this.tasks);
        return deletedTask;
    }
}
