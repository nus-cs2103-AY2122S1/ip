package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class Duke {
    /** Hardcoded file path to save location. */
    private static final String FILEPATH = "./data/tasks.json";

    /** The storage utility. */
    private Storage storage;

    /** The list of tasks. */
    private TaskList tasks;

    /** The program ui. */
    private Ui ui;

    /**
     * Duke constructor.
     *
     * @param filePath The file path to save location.
     */
    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui.showLoadSuccess();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Entry point for the program.
     *
     * @throws IOException If the ui encounters error while reading user input.
     */
    private void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /** Driver method. */
    public static void main(String[] args) {
        try {
            new Duke(FILEPATH).run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
