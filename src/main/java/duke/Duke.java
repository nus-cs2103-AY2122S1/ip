package duke;

import duke.command.Command;

/**
 * Main driver for Duke chat bot.
 *
 * @author nzixuan
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor of Duke.
     *
     * @param filePath file path of to retrieve save file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    /**
     * Initialise Duke with TaskList.
     *
     * @return Initial Duke Response
     */
    public String initialize() {
        try {
            tasks = new TaskList(storage.load(), storage);
            return ui.greet();
        } catch (DukeException e) {
            tasks = new TaskList(storage);
            return ui.showLoadingError() + ui.greet();
        }
    }

    public String getResponse(String input) {
        boolean isExit = false;
        try {
            Command c = Parser.parse(input);
            isExit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.printError(e.getMessage());
        }
    }
}
