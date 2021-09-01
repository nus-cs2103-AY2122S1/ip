package duke;

import duke.Command.Command;
import duke.Exceptions.DukeException;
import duke.Parser.Parser;
import duke.Storage.Storage;
import duke.Tasks.TaskList;
import duke.Ui.Ui;

public class Duke {
    private final Storage storage;
    private final Ui ui;
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
