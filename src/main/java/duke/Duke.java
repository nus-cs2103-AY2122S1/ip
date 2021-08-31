package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * A terminal based chat bot to track tasks.
 */
public class Duke {

    private final Storage storage;
    private final Ui ui;
    private TaskList taskList;

    /**
     * Duke constructor.
     *
     * @param filePath The filepath of the task list from the project root
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Main method that starts and runs the bot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
                storage.save(taskList);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasklist.txt").run();
    }
}
