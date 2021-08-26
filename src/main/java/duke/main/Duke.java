package duke.main;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                isExit = Parser.parse(fullCommand, taskList, ui, storage);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

    }


    public static void main(String[] args) throws IOException {
        new Duke("duke.txt").run();
    }
}