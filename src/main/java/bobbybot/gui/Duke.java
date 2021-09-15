package bobbybot.gui;

import java.util.ArrayList;

import bobbybot.commands.Command;
import bobbybot.util.Parser;
import bobbybot.util.PersonList;
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
    private final PersonList contacts;
    /**
     * Initialise Duke object with filepath of storage
     * @param filePath storage .txt file path
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        tasks = new TaskList(storage.load());
        parser = new Parser();
        contacts = new PersonList(new ArrayList<>());
        //todo add storage feature for contacts
    }

    /**
     * Get response of BobbyBot from user input
     */
    String getResponse(String input) {
        Command c = parser.parseCommand(input);
        assert c != null : "Command cannot be null";
        c.execute(tasks, ui , storage, contacts);
        return c.getResponse();
    }
}
