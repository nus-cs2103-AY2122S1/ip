package duke;

import duke.command.Command;

import task.TaskList;

/**
 * Main Duke class
 */
public class Duke {
    private final DukeParser parser;

    /**
     * Constructor
     * Instantiates a (saved) task.Task List and a duke.DukeListener for duke.Duke
     */
    protected Duke() {
        TaskList taskList = Storage.loadList();
        this.parser = new DukeParser(taskList);
    }

    /**
     * Get response from duke from the GUI
     *
     * @param input String input from user
     */
    protected String getResponse(String input) {
        // TaskList related inputs
        Command cmd = parser.parseInput(input);
        return cmd.execute();
    }
}
