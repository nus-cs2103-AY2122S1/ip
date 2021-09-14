package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Parser;
import duke.ui.Ui;

/**
 * Represents a Personal Assistant Chatbot that helps a person keeps track of various things.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList list;

    /**
     * Constructor specifying the file path of the database to be stored and retrieved from for this bot.
     *
     * @param filePath File path to store and retrieve the duke bot information.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.list = new TaskList(storage.loadDukeData());
        } catch (DukeException e) {
            this.ui.printAndReturnMessage(e.getMessage());
            this.list = new TaskList();
        }
    }

    /**
     * Returns the response given a String input.
     *
     * @param input The input String given.
     * @return The String that represents the response to the input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(list, ui, storage);
        } catch (DukeException e) {
            ui.printAndReturnMessage(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Returns the greeting message as a String.
     *
     * @return The message to greet users as a String.
     */
    public String getGreetingMessage() {
        return ui.printAndReturnGreetingsString();
    }
}

