package duke;

import duke.commands.Command;
import java.io.FileNotFoundException;
import duke.exceptions.DukeException;
import javafx.application.Platform;

/**
 * Duke object class,
 * contains methods to run the duke bot.
 */
public class Duke {

    /**
     * Helps convert string input by user
     * to commands.
     */
    private final Parser parser;

    /**
     * Helps in storing tasks and retrieving
     * when required by user.
     */
    private Storage storage;

    /**
     * Helps in displaying tasks to user.
     */
    private Ui ui;

    /**
     * Keeps track of tasks while duke
     * program is running.
     */
    private TaskList tasks;


    /**
     * Instantiates Parser, Storage, Ui and
     * taskList and passes the filepath to storage class.
     * Catches both exceptions from storage class.
     * If either 1 exception happens, taskList is
     * initialized as empty, with no tasks in it.
     * @param filePath String containing the relative file path
     * that storage class takes in to store and read tasks.
     */
    public Duke(String filePath) {
        parser = new Parser();
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Gives the starting welcome message.
     * @return String welcome message.
     */
    public String getWelcomeMessage() {
        return ui.showWelcomeMsg();
    }

    /**
     * Retrieves the response of the bot
     * given the input command by user.
     * Interacts with the different classes
     * to achieve the bot behaviour.
     * Run stops when a "bye" command is
     * entered and ends the bot processes.
     * @param fullCommand String of command given
     * by user.
     * @return String of output to be displayed
     * to user via UI.
     */
    public String run(String fullCommand) {
        try {
            Command c = parser.parse(fullCommand);
            String outputMessage = c.execute(tasks, ui, storage);
            if (c.isExit()) {
                Platform.exit();
            }
            return outputMessage;
        } catch (DukeException e) {
            return ui.displayErrorMessage(e.getMessage());
        }
    }
}
