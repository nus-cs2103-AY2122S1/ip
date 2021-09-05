package duke;

import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes Duke object
     * @param filePath The file where tasks are stored
     */
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

    /**
     * Runs the Duke bot.
     */
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
        new Duke("./data/tasks.txt").run();
    }
}
