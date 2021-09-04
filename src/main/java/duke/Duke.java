package duke;

import duke.command.Command;
import duke.error.DukeException;
import duke.task.TaskList;

/**
 * A chatbot that helps keep track of various tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Constructs a Duke object.
     *
     * @param directoryPath Path to data file directory.
     * @param filePath Path to the data file.
     */
    public Duke(String directoryPath, String filePath) {
        ui = new UI();
        storage = new Storage(directoryPath, filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExitCommand();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("./data/", "duke.txt").run();
    }
}
