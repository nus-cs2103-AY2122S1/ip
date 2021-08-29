package Duke;

import Duke.Command.Command;
import Duke.Exceptions.DukeException;
import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Initializes a new Duke Object so that it can run the programme.
     *
     * @param path relative position of the txt file
     */
    public Duke(String path) {
        ui = new Ui();
        storage = new Storage(path);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke Object by calling introduceDuke() from the Ui class. It is capable of
     * Understanding user's commands and catching errors when thrown.
     */
    public void run() {
        ui.introduceDuke();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = "";
            try {
                fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * This is the main file for this java programme.
     *
     * @param args There is no arguments that is required to run this programme.
     */
    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }

}
