package tokio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import tokio.commands.Command;
import tokio.exceptions.DukeException;
import tokio.parser.Parser;
import tokio.storage.Storage;
import tokio.tasks.TaskList;
import tokio.ui.Ui;

/**
 * A chatbot that manages user input tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String path = "data/tasks.txt";

    /**
     * Creates a Duke, initialise Ui, Storage and TaskList.
     *
     * @param path File path of storage.
     * @throws IOException If file cannot be loaded.
     * @throws DukeException If user input is unknown.
     */
    public Duke() throws IOException, DukeException {
        ui = new Ui();
        storage = new Storage(path);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException | DukeException | ParseException e) {
            tasks = new TaskList();
            throw new DukeException("Oh no, there seems to be a problem...");
        }
    }

    /**
     * Creates a Duke, initialise Ui, Storage and TaskList.
     *
     * @param filePath File path of storage.
     * @throws IOException If file cannot be loaded.
     * @throws DukeException If user input is unknown.
     */
    public Duke(String filePath) throws IOException, DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException | DukeException | ParseException e) {
            tasks = new TaskList();
            throw new DukeException("Oh no, there seems to be a problem...");
        }
    }

    /**
     * Starts duke.
     *
     * @throws DukeException If user input is unknown.
     */
    public void run() throws DukeException {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                throw new DukeException("Oh no, there was a problem loading this file...");
            }
        }
    }

    /**
     * Main function for Duke.
     *
     * @throws IOException If file cannot be loaded.
     * @throws DukeException If user input is unknown.
     */
    public static void main(String[] args) throws IOException, DukeException {
        Duke duke = new Duke("data/tasks.txt");
        duke.run();
    }

    /**
     * To generate a response to user input.
     *
     * @param input User input in string.
     * @return Tokio's output to user input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input.trim());
            if (command.isExit()) {
                ui.printBye();
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(event -> System.exit(0));
                delay.play();
            }
            return command.execute(tasks, ui, storage);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}
