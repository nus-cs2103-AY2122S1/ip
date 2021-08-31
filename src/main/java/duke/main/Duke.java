package duke.main;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The Duke program implements an application that takes in user input and acts as task list manager for users.
 * @author  Nicholas Loo
 * @version 0.1
 * @since   2021-08-25
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class Constructor specifying the file path where data is stored.
     * @param filePath The file path giving the location of the file containing user data
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readPastData());
        } catch (FileNotFoundException e) {
            storage.createDataFile();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Returns the Storage associated with the Duke application.
     * @return Storage The class handling the writing/reading of data
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Returns the TaskList storing all the Task.
     * @return TaskList The TaskList storing the Task.
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Returns the Ui handling input/output.
     * @return Ui The Ui handling input/output.
     */
    public Ui getUi() {
        return ui;
    }
}
