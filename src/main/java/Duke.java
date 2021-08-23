public class Duke {
    private boolean inSession;
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Initialises storage, taskList and ui.
     *
     * @param filePath the relative filePath for storage of dataFile.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showError(e);
            taskList = new TaskList();
        }
    }

    /**
     * Runs Duke until the user inputs a bye command.
     */
    public void run() {
        inSession = true;
        Parser parser = new Parser(taskList, storage);
        ui.greet();
        while (inSession) {
            try {
                inSession = parser.parseCommand(ui.scan());
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
