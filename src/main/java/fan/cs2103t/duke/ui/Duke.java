package fan.cs2103t.duke.ui;

import fan.cs2103t.duke.command.Command;
import fan.cs2103t.duke.command.ExitCommand;
import fan.cs2103t.duke.exception.DukeException;
import fan.cs2103t.duke.parser.Parser;
import fan.cs2103t.duke.storage.Storage;
import fan.cs2103t.duke.task.TaskList;

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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String output;
        try {
            Command command = parser.parseCommand(input);
            String tmp = command.execute(tasks, ui);
            output = tmp == null ? "NULL" : tmp;
            storage.saveTaskList(tasks); // save the latest task list no matter what command it is
            if (ExitCommand.isExit(command)) {
                System.exit(0); // TODO: better handle this
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            output = e.getMessage();
        }
        return output;
    }

}
