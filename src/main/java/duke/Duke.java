package duke;

import duke.command.Command;
import java.io.IOException;

public class Duke {

    /** The storage utility */
    private Storage storage;

    /** The list of tasks */
    private TaskList tasks;

    /** The UI for the program */
    private Ui ui;

    /** The filepath for storage */
    private static final String FILE_PATH = "data/tasks.txt";

    /**
     * The constructor for Duke class
     *
     * @param filePath file path to be used for storage
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * The method to start the program
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}
