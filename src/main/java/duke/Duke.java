package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DataFileChangedException;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;

/**
 * Duke class that initialises the duke.Duke chat bot.
 * The Duke class supports operators including
 * (i) run: runs the chat bot
 */

public class Duke {

    /** Path to data storage file. */
    private static final String LOCATION_OF_FILE = "data/duke.txt";

    /** Path to expenses data file */
    private static final String LOCATION_OF_EXPENSES = "data/expenses.txt";

    /** Handles loading and saving of Tasks. */
    private final Storage storage;

    /** Stores all tasks */
    private TaskList tasks;

    /** Handles interactions with users. */
    private final Ui ui;

    /** Parses commands by users for Duke to understand. */
    private final Parser parser;

    /**
     * Constructor for the Duke chat bot.
     * Loads any pre-existing data from the provided filePath.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(LOCATION_OF_FILE, LOCATION_OF_EXPENSES);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | DukeException e) {
            tasks = new TaskList();
            ui.printMessage(e.getMessage());
        } finally {
            parser = new Parser();
        }
    }

    /**
     * Runs the command line version of the Duke chat bot.
     */
    private void run() {

        ui.cliGreet();

        boolean isExit = false;
        /* user input trimmed to remove unwanted spaces at the front and back of user input
        allows for greater margin of error when typing in commands */
        while (!isExit) {
            // continuously runs the bot as long as the "bye" command is not issued
            String input = ui.getNextLine();

            try {
                Command cmd = parser.handleCommands(input);
                String response = cmd.execute(tasks, ui, storage);
                assert response != null : " Duke's response missing";
                isExit = cmd.isExit();
                ui.printMessage(response);
            } catch (DukeException e) {
                ui.printMessage(e.getMessage());
            } catch (IOException e) {
                ui.printMessage(new DataFileChangedException().getMessage());
            }
        }
    }

    /**
     * Runs the Duke chat bot.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command cmd = parser.handleCommands(input);
            boolean isExit = cmd.isExit();
            if (isExit) {
                Platform.exit();
            }
            String response = cmd.execute(tasks, ui, storage);
            assert response != null : " Duke's response missing";
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return new DataFileChangedException().getMessage();
        }
    }
}
