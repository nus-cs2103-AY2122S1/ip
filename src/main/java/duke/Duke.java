package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Main class of the project.
 */
public class Duke {

    private Storage storage;
    private final Ui ui;
    private final TaskList tasks;

    /**
     * Private constructor to initialize other components.
     *
     * @param filePath path to the log file to be read by Storage.
     */
    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
        storage = new Storage(filePath);
    }

    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        String userPath = System.getProperty("user.dir")
                + "\\data\\save.txt";
        new Duke(userPath).run();
    }

    /**
     * Main body to loop through reading commands and displaying outputs.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            ui.showLine();
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            ui.showLine();
        }
    }
}
