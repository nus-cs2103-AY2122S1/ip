package jackie;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import jackie.command.Command;

/**
 * A chatbot programme that executes a series of commands from user input and helps keep track user's tasks.
 *
 * @author Gu Geng
 */
public class Jackie {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns an Jackie instance with storage tasklist and ui initialised.
     *
     * @param filePath the directory to stored data as a Path.
     */
    public Jackie(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JackieException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Executes the Jackie programme.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand.split(" "));
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JackieException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Drives the Jackie programme.
     *
     * @param args A String array from user input
     * @throws JackieException Exception that is specific to Jackie where invalid input is detected
     */
    public static void main(String[] args) throws JackieException, IOException {
        String currDir = new File("").getAbsolutePath();
        Path filePath = Paths.get(currDir, "data", "jackie.txt");
        new Jackie(filePath).run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input.split(" "));
            return c.execute(tasks, ui, storage);
        } catch (JackieException e) {
            return "Error: " + e.getMessage();
        }
    }
}
