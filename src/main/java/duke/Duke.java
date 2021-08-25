package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Duke(String persistedData) {
        ui = new Ui();
        storage = new Storage(persistedData);
        tasks = new TaskList(storage.loadPersistedData());
    }

    public void run() {
        boolean toExit = false;
        ui.showWelcome();
        while (!toExit) {
            String fullCommand = ui.readCommand();
            ui.showLines();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                toExit = c.isExitCommand();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLines();
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
