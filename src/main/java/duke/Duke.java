package duke;

import java.io.IOException;

import duke.commands.Command;

/**
 * Container for Duke program.
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Instantiates a Duke object.
     *
     * @param filePath String of file path of the previously saved task.txt file (if any).
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * Executes the Duke program.
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();

            } catch (DukeException e) {
                ui.printError(e.toString());
            }
        }
    }

    /**
     * Drives the Duke program.
     *
     * @param args Arguments that user inputs.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
