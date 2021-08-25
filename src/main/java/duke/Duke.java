package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

public class Duke {
    private final Storage storage;
    private TaskManager taskManager;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskManager = new TaskManager(storage.loadTasks());
        } catch (DukeException e) {
            ui.print(e.getMessage());
            taskManager = new TaskManager();
        }
    }

    public void run() {
        ui.greet(taskManager);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(taskManager, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
