package duke;

import duke.command.Command;


/**
 * Duke is a personal assistant that keep a list of your tasks. Users
 * are able to add different types of tasks to their own list as well as
 * mark tasks as completed or delete tasks from their task list.
 *
 * @author leezhixuan
 */

public class Duke {

    private static String name = "Duke";
    private boolean isRunning = true;
    private ToDoList tdl;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public Duke() {
        this.ui = new Ui(Duke.name);
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        Command c = this.parser.parse(input, this.tdl, this.ui, this, this.storage);
        String response = c.execute();
        return Duke.name + " heard: \n" + response;
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
