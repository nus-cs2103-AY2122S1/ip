package catobot;

import catobot.command.Command;
import catobot.command.Parser;
import catobot.exception.BotException;
import catobot.exception.LoadingException;
import catobot.item.TaskList;

/**
 * Represents a Catobot.
 */
public class Catobot {
    /** The name of Catobot. */
    public static final String NAME = "Catobot";

    /** The welcome message of Catobot. */
    public static final String WELCOME =
            String.format("Hello I am %s (>^^<)\nWhat can I do for you meow?", Catobot.NAME);

    /** The exit message of Catobot. */
    public static final String BYE =
            "Bye meow! I will always wait here meow(>^^<)";

    /** Storage of the tasks. */
    private Storage storage;

    /** List of tasks for the Catobot. */
    private TaskList tasks;

    /**
     * Constructor for Catobot.
     *
     * @param filePath Location of the local memory.
     */
    Catobot(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (LoadingException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Gets the responses for user input.
     *
     * @param input The input from the user.
     * @return The text to display in the window.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (BotException e) {
            return e.getMessage();
        }
    }

}
