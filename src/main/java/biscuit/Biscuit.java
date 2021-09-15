package biscuit;

import java.util.ArrayList;

import biscuit.exceptions.BiscuitException;
import biscuit.storage.Storage;
import biscuit.task.TaskList;

/**
 * Biscuit is a Personal Assistant Chat-bot that helps a person to keep track of various things.
 */
public class Biscuit {
    private final Storage storage;
    private TaskList taskList;

    /**
     * Constructs Biscuit class.
     *
     * @param filePath file path to save file.
     */
    public Biscuit(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (BiscuitException e) {
            taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Gets storage in Biscuit.
     *
     * @return Storage.
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Gets task list in Biscuit.
     *
     * @return Task list.
     */
    public TaskList getTaskList() {
        return taskList;
    }

}
