package tokio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
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
     */
    public Duke() throws IOException {
        ui = new Ui();
        storage = new Storage(path);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException | DukeException | ParseException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Creates a Duke, initialise Ui, Storage and TaskList.
     *
     * @param filePath File path of storage.
     * @throws IOException If file cannot be loaded.
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException | DukeException | ParseException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts duke.
     */
    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.printFileNotFoundError();
            }
        }
    }

    /**
     * Main function for Duke.
     */
    public static void main(String[] args) throws IOException {
        Duke duke = new Duke("data/tasks.txt");
        duke.run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            if (command.isExit()) {
                ui.printBye();
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(event -> Platform.exit());
                delay.play();
            }
            return command.execute(tasks, ui, storage);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}
