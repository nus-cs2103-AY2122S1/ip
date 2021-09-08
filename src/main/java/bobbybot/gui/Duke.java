package bobbybot.gui;

import bobbybot.commands.Command;
import bobbybot.exceptions.BobbyException;
import bobbybot.util.Parser;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

/**
 * This class wraps the cli BobbyBot
 */
public class Duke {

    private final Parser parser;
    private final Storage storage;
    private final Ui ui;
    private final TaskList tasks;
    /**
     * Initialise Duke object with filepath of storage
     * @param filePath storage .txt file path
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    /**
     * Get response of BobbyBot from user input
     */
    String getResponse(String input) {
        try {
            Command c = parser.parseCommand(input);
            c.execute(tasks, ui , storage);
            return c.getResponse();
        } catch (BobbyException e) {
            return e.getMessage();
        }
    }
}
