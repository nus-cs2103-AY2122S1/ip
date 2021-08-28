package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Store;
import duke.util.Tasklist;
import duke.util.Ui;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: A-Gradle. Add gradle support
 *
 * Description:
 * On running the program, Duke greets the user and awaits for inputted text.
 * Program stores whatever text entered by the user and display them back
 * to the user when requested.
 *
 * @author Keith Tan
 */
public class Duke {

    private Tasklist taskList;
    private Store storage;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Store(filePath);
        try {
            taskList = storage.load();
        } catch (DukeException e) {
            ui.printErrorMessage(e);
            taskList = new Tasklist();
        }
    }

    /**
     * Initializes the Duke chat bot and runs the chat bot
     *
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseCommandString(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {

        new Duke("/Users/keithtan/Desktop/NUS/CS2103 IP/ip/data/duke.txt").run();

    }
}