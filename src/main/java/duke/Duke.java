package duke;

import duke.command.Command;
import duke.util.DukeParser;
import duke.util.Storage;
import task.TaskList;

/**
 * Main Duke class.
 */
public class Duke {
    private final DukeParser parser;

    /**
     * Constructor.
     * Instantiates a (saved) {@link task.TaskList} and a {@link DukeParser}.
     */
    protected Duke() {
        TaskList taskList = Storage.loadList();
        this.parser = new DukeParser(taskList);
    }

    /**
     * Gets response from duke from the GUI.
     *
     * @param input String input from user.
     */
    public Command getResponse(String input) {
        return parser.parseInput(input);
    }
}
