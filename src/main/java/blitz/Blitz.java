package blitz;

public class Blitz {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates blitz.Blitz object with given file path.
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
     * Returns blitz.Blitz's storage object.
     *
     * @return blitz.Blitz's storage object.
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Returns list of blitz.tasks.
     *
     * @return list of blitz.tasks.
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Returns blitz.Blitz's blitz.Ui.
     *
     * @return blitz.Blitz's blitz.Ui.
     */
    public Ui getUi() {
        return ui;
    }
}
