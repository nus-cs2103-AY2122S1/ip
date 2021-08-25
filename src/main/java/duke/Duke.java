package duke;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;
import duke.util.TaskList;

import duke.command.Command;

import duke.exception.DukeException;

public class Duke {
    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String[] fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/saveFile.txt").run();
    }
}