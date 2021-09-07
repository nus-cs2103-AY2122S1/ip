package duke;

import duke.util.*;

/**
 * Represents the core of Duke.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    /**
     * Represents a new Duke project.
     * @param filePath of the database
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
        parser = new Parser(taskList, ui, storage);
    }

    /**
     * Represents a new Duke project without parameter.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/tasks.txt");
        taskList = new TaskList(storage.load());
        parser = new Parser(taskList, ui, storage);
    }

    /**
     * Show welcome to the user.
     * @return welcome content
     */
    public String welcome() {
        return ui.showWelcome();
    }

    /**
     * Gets corresponding response from Duke.
     * @param input user input
     * @return Result content
     */
    public String getResponse(String input) {
        try {
            return parser.parse(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
