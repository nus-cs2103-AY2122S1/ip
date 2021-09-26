package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a personal assistance to manage users' tasks list.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke() {}

    /**
     * Sets up the Duke program by instantiating a user interface, a storage and a task list.
     *
     * @param filePath File path of the storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.toString());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showOpeningLine();
                Command c = Parser.parse(input);
                String response = c.executeAndGetResponse(tasks, ui, storage);
                ui.showResponse(response);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.toString());
            } finally {
                ui.showClosingLine();
            }
        }
        ui.cleanup();
    }

    /**
     * Parses, executes and gets response from user input.
     *
     * @param input User input.
     * @return Response of command.
     */
    public String parseExecuteAndGetResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.executeAndGetResponse(tasks, ui, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
