package blitz;

public class Blitz {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates Blitz object with given file path.
     *
     * @param filePath
     */
    public Blitz(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadFileContents());
        } catch (BlitzException e) {
            ui.getLoadErrorMessage();
            tasks = new TaskList();
        }
    }

    /**
     * Returns Blitz's storage object.
     *
     * @return Blitz's storage object.
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Returns list of tasks.
     *
     * @return list of tasks.
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Returns Blitz's Ui.
     *
     * @return Blitz's Ui.
     */
    public Ui getUi() {
        return ui;
    }
}
