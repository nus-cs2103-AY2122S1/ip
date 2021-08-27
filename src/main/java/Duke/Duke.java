package Duke;

/**
 * The main class for Duke.
 */
public class Duke {

    private Ui ui;
    private TaskManager taskManager;
    private Storage storage;
    private boolean isRunning = true;
    private Parser parser = new Parser();

    /**
     * Constructor for Duke.
     *
     * @param saveFileLocation The path to where the save file is to be stored.
     */
    public Duke(String saveFileLocation) {
        ui = new Ui();
        storage = new Storage(saveFileLocation);
        taskManager = new TaskManager(storage.loadSave());
    }

    public static void main(String[] args) {
        new Duke("docs\\save.txt").run();
    }

    /**
     * Runs the program until the bye command is used to end it.
     */
    private void run() {
        ui.introduceYourself();

        while(isRunning) {
            ICommand c = parser.getInput();
            c.execute(taskManager, ui, storage);
        }
    }


}
