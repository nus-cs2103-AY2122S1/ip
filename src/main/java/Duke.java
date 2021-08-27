import duke.*;

/**
 * Encapsulates the entire application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(storage.load());
        }
    }

    /**
     * Runs the duke program.
     */
    public void run() {
        ui.displayWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        ui.closeScanner();
    }

    /**
     * Creates a new instance of duke with a hardcoded filepath then runs it.
     *
     * @param args not relevant to Duke.
     */
    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}

