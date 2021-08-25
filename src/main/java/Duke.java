import duke.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.exceptions.DukeException;

/** This is the main class for the program. */
public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private static final String FILE_PATH = "data.txt";
    private final Ui ui;

    /**
     * Construct an instance of Duke program.
     *
     * @param filePath The path of the file to load saved data from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    
    /** Start of the program. */
    public void run() {
        ui.welcomeMessage();
        boolean isExit = false;

        // Main loop of program
        while (!isExit) {
            try {
                // Deal with user's input
                ui.enterCommand();
                String fullCommand = ui.readInput();
                ui.lineGenerator();

                // Execute command based on user's input
                duke.parser.Parser parser = new duke.parser.Parser(fullCommand);
                isExit = parser.execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.printError(e);
            } finally {
                ui.lineGenerator();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}
