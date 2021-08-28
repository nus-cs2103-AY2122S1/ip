package duke;

import java.nio.file.Paths;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This Duke class implements the functionalities of a chatbot,
 * helping a person to keep track of various things.
 */
public class Duke {

    private static final String FILE_PATH = String.valueOf(Paths.get(
            System.getProperty("user.home"), "data", "dukeFile.txt"));

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructor for a Duke instance.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    /**
     * Listens to the user input and executes the commands.
     */
    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showError("Please specify the date in this format: yyyy-mm-dd");
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
