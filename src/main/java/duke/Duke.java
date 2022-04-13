package duke;

import duke.command.Command;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.ToDoList;


/**
 * Duke is a personal assistant that keep a list of your tasks. Users
 * are able to add different types of tasks to their own list as well as
 * mark tasks as completed or delete tasks from their task list.
 *
 * @author leezhixuan
 */

public class Duke {

    private static String name = "Shin";
    private boolean isRunning = true;
    private ToDoList tdl;
    private Storage storage;
    private Parser parser;

    /**
     * Creates a Duke object.
     */
    public Duke() {
        this.tdl = new ToDoList(Duke.name);
        this.storage = new Storage(this.tdl);
        this.storage.reloadTask();
        this.parser = new Parser();
    }

    public static String getName() {
        return name;
    }

    /**
     * Stops the chat bot.
     */
    public void stopRunning() {
        this.isRunning = false;
    }

    /**
     * Processes user input in order to generate the appropriate response.
     *
     * @param input Input from user
     * @return response from the chat bot
     */
    public String getResponse(String input) {
        Command c = this.parser.parse(input, this.tdl, this, this.storage);
        assert c != null : "How did you even get to this point?";
        return c.execute();
    }

    /**
     * Checks if Duke is still running
     *
     * @return true if Duke is still running, false otherwise
     */
    public boolean isRunning() {
        return isRunning;
    }
}
