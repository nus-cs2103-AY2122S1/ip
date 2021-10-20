package duke;

import duke.command.Command;

/**
 * Main driver for Duke chat bot.
 *
 * @author nzixuan
 */
public class Duke {

    private final Storage STORAGE;
    private TaskList tasks;
    private final Ui UI;

    /**
     * Constructor of Duke.
     *
     * @param filePath file path of to retrieve save file
     */
    public Duke(String filePath) {
        UI = new Ui();
        STORAGE = new Storage(filePath);
    }

    /**
     * Initialise Duke with TaskList.
     *
     * @return Initial Duke Response
     */
    public String initialize() {
        try {
            tasks = new TaskList(STORAGE.load(), STORAGE);
            return UI.greet();
        } catch (DukeException e) {
            tasks = new TaskList(STORAGE);
            return UI.showLoadingError() + UI.greet();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null;
            return c.execute(tasks, UI, STORAGE);
        } catch (DukeException e) {
            return UI.printError(e.getMessage());
        }
    }
}
