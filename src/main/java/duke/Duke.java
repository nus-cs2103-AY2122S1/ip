package duke;

/**
 * The Duke Application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke class.
     * Initialises the UI, Storage and loads tasks from data file into Task List.
     *
     * @param dataFolderPath Path to data folder.
     * @param dataFilePath Path to data file.
     */
    public Duke(String dataFolderPath, String dataFilePath) {
        ui = new Ui();
        storage = new Storage(dataFolderPath, dataFilePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Duke application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage, ui);
                isExit = c instanceof ExitCommand;
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main function.
     *
     * @param args Program input arguments.
     */
    public static void main(String[] args) {
        new Duke("./data", "./data/tasks.txt").run();
    }
}
