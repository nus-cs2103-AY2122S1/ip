package ui;

import duke.DukeException;
import storage.Storage;
import tasks.TaskList;

/**
 * The DukeApp class is a workaround to access Storage, TaskList, Ui and UserPrompts.
 */
final class DukeApp {
    private final TaskList list;
    private final Ui ui;
    private final Storage storage;
    private final UserPrompts userPrompts;

    /**
     * Constructs a DukeApp object.
     */
    public DukeApp() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            storage.createFiles();
        } catch (DukeException e) {
            System.out.println("something went wrong: "
                            + e.getMessage() + "\n" + "     exiting program...");
            System.exit(0);
        }
        list = new TaskList(storage.loadSaves());
        userPrompts = new UserPrompts();
    }

    /**
     * Retrieves an instance of Ui object.
     * @return an ui object
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Retrieves an instance of TaskList object.
     * @return a TaskList object
     */
    public TaskList getLst() {
        return list;
    }

    /**
     * Retrieves an instance of storage object.
     * @return a storage object
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Retrieves an instance of UserPrompts object.
     * @return a userPrompts object
     */
    public UserPrompts getUserPrompts() {
        return userPrompts;
    }
}
