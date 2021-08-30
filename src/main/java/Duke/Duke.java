package Duke;

import Duke.Command.Command;
import Duke.Exceptions.DukeException;
import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

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

    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }

}
