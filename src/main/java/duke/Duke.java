package duke;

import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                storage.writeFile(tasks);
                isExit = (c instanceof ExitCommand);
            } catch (DukeException | IOException e) {
                ui.displayError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
