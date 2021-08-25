package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * A chat-bot called Naruto that acts as a task list
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for the Duke chat-bot
     *
     * @param persistedData the relative path to the persisted data starting from the project directory
     */
    public Duke(String persistedData) {
        ui = new Ui();
        storage = new Storage(persistedData);
        tasks = new TaskList(storage.loadPersistedData());
    }

    /**
     * Initializes and starts the chat-bot for operation/interaction
     */
    public void run() {
        boolean toExit = false;
        ui.showWelcome();
        while (!toExit) {
            String fullCommand = ui.readCommand();
            ui.showLines();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                toExit = c.isExitCommand();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLines();
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
