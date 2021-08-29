package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.command.Command;

/**
 * A chatbot programme that executes a series of commands from user input and helps keep track user's tasks.
 *
 * @author Gu Geng
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns an Duke instance with storage tasklist and ui initialised.
     *
     * @param filePath the directory to stored data as a Path.
     */
    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Executes the Duke programme.
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
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Drives the Duke programme.
     *
     * @param args A String array from user input
     * @throws DukeException Exception that is specific to Duke where invalid input is detected
     */
    public static void main(String[] args) throws DukeException, IOException {
        String currDir = new File("").getAbsolutePath();
        Path filePath = Paths.get(currDir, "data", "duke.txt");
        new Duke(filePath).run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
