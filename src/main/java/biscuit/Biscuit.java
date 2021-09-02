package biscuit;

import java.util.ArrayList;

import biscuit.exceptions.BiscuitException;
import biscuit.storage.Storage;
import biscuit.task.TaskList;
import biscuit.ui.Ui;

/**
 * Biscuit is a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Biscuit {
    private final Storage storage;
    private final Ui ui;
    private TaskList taskList;

    /**
     * Constructs Biscuit class.
     *
     * @param filePath file path to save file.
     */
    public Biscuit(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            taskList = new TaskList(storage.load());
        } catch (BiscuitException e) {
            ui.showMessage(e.getMessage());
            taskList = new TaskList(new ArrayList<>());
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public Ui getUi() {
        return ui;
    }

    public TaskList getTaskList() {
        return taskList;
    }

}
