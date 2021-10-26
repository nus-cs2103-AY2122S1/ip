package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the initialization of Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    // Fields for chatbot functionality
    public static final String FILEPATH = "data/tasks.txt";
    public static final String ARCHIVE_FILEPATH = "data/archive.txt";
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private boolean isRunning = true;

    /**
     * @param filePath File path to read/write task list data to.
     * @throws IOException If there is an error reading/writing to the filepath.
     */
    public Duke(String filePath, String archiveFilePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath, archiveFilePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }

    }

    /**
     * Default constructor for Duke application.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH, ARCHIVE_FILEPATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println("Critical failure while reading io");
        }
    }

    /**
     * Parses user input and gets appropriate response from Duke.
     *
     * @param input User command input.
     * @return String representation of Duke response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            if (!c.isActive()) {
                isRunning = false;
            }
            return c.getCommandOutput();
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Please enter the date and time "
                    + "in dd/mm/yyyy hh:mma format!";
        }
    }

    /**
     * Checks whether the duke application is supposed to be running.
     *
     * @return true If application is meant to be running, false otherwise.
     */
    public boolean getActiveStatus() {
        return this.isRunning;
    }
}
