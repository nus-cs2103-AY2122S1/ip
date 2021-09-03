package bobbybot.gui;

import bobbybot.commands.Command;
import bobbybot.exceptions.InvalidArgumentException;
import bobbybot.util.Parser;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

import java.io.FileNotFoundException;

/**
 * This class wraps the cli BobbyBot
 */
public class Duke {

    private final Parser parser;
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;
    /**
     * Initialise Duke object with filepath of storage
     * @param filePath storage .txt file path
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        tasks = null;
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load storage");
            e.printStackTrace();
        }
        parser = new Parser();
    }

    /**
     * Get response of BobbyBot from user input
     */
    String getResponse(String input) {
        Command c = null;
        try {
            c = parser.parseCommand(input);
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
        return c.getResponse(tasks, ui, storage);
    }
}