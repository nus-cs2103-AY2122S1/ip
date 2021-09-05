package duke.util;
import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents the storage operation of a hard disk.
 */
public class Storage {
    private Database database;

    public Storage(String filePath) {
        database = new Database(filePath);
    }

    /**
     * Loads all data from the hard disk.
     *
     * @return Task List
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = database.readData();
        return tasks;
    }

    /**
     * Adds a task item into the hard disk.
     *
     * @param task new item to be added
     */
    public void add(Task task) {
        database.addData(task);
    }

    /**
     * Marks a task in the hard disk as Done.
     *
     * @param index the index of Task
     */
    public void done(int index) {
        database.doneData(index);
    }

    /**
     * Deletes a task from the hard disk.
     *
     * @param index the index of Task
     */
    public void delete(int index) {
        database.deleteData(index);
    }
}
