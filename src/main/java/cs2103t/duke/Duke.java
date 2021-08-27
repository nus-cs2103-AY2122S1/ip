package cs2103t.duke;

import cs2103t.duke.command.Command;
import cs2103t.duke.command.ExitCommand;
import cs2103t.duke.exception.DukeException;
import cs2103t.duke.parser.Parser;
import cs2103t.duke.storage.Storage;
import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

/**
 * Implementation of Duke, a personal assistant chatterbot that
 * helps a person to keep track of various things.
 */
public class Duke {

    private final Storage storage;
    private final Parser parser;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs Duke with the specified file path.
     * <p>
     * Initializes the UI manager, the storage manager, and the parser.
     * Duke's data will be stored in the file denoted by the specified file path.
     *
     * @param filePath the specified file path to store Duke's data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = storage.retrieveTaskList();
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        do {
            try {
                String input = ui.readLine();
                Command command = parser.parseCommand(input);
                command.execute(tasks, ui);
                storage.saveTaskList(tasks); // save the latest task list no matter what command it is
                isExit = ExitCommand.isExit(command);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        } while (!isExit);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
