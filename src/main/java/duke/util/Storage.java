package duke.util;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the storage operation of a hard disk.
 */
public class Storage {
    DukeDB dukeDb;

    public Storage(String filePath) {
        dukeDb = new DukeDB(filePath);
    }

    /**
     * Loads all data from the hard disk.
     *
     * @return Task List
     */
    public ArrayList<Task> load(){
        ArrayList<Task> tasks = dukeDb.readData();
        return tasks;
    }

    /**
     * Adds a task item into the hard disk.
     *
     * @param task new item to be added
     */
    public void add(Task task) {
        dukeDb.addData(task);
    }

    /**
     * Marks a task in the hard disk as Done.
     *
     * @param index the index of Task
     */
    public void done(int index) {
        dukeDb.doneData(index);
    }

    /**
     * Deletes a task from the hard disk.
     *
     * @param index the index of Task
     */
    public void delete(int index) {
        dukeDb.deleteData(index);
    }
}
