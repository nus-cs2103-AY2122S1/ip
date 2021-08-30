package ponyo;

import ponyo.commands.Command;
import ponyo.data.exceptions.PonyoException;
import ponyo.data.task.TaskList;
import ponyo.parser.Parser;
import ponyo.storage.Storage;
import ponyo.ui.Ui;

public class Ponyo {
    // CONSTANTS
    private static final String PATH = "src/main/data";
    private static final String FILENAME = "tasks.txt";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Create a new Ponyo program instance
     *
     * @param filePath indicates where to store the hardcopy of tasks
     */
    public Ponyo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (PonyoException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program until termination: exit
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (PonyoException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Ponyo(PATH + "/" + FILENAME).run();
    }
}
