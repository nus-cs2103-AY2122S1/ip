package duke;

import duke.tasks.Task;
import duke.utils.DukeException;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final static DukeException ERROR_DB = new DukeException("Error loading database.");
    private List<Task> db = new ArrayList<>();
    private Storage storage;
    
    public TaskList() throws DukeException {
        this.storage = new Storage(this);
    }

    // fix the adding problems
    public void add(Task task) throws DukeException {
        db.add(task);
        if (storage != null) storage.update(task);
    }
    
    public Task markAsDone(int index) throws DukeException {
        Task t = db.get(index);
        t.markComplete();
        if (storage != null) storage.update(this);
        return t;
    }

    public Task delete(int index) throws DukeException {
        Task t = db.get(index);
        db.remove(index);
        if (storage != null) storage.update(this);
        return t;
    }

    public int size() {
        return db.size();
    }
    
    public void close() throws DukeException {
        storage.close();
    }
    
    public List<Task> getList() {
        return db;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (db.size() == 0) return "You have no tasks!";
        for (int i = 1; i <= db.size(); i++) {
            sb.append("\n\t ");
            sb.append(i + "." + db.get(i-1));
        }
        sb.deleteCharAt(0);
        return sb.toString();
    }
}
