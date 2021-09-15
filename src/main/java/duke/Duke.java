package duke;

import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Duke {

    /** Storage object to interact with the data file */
    private Storage storage;
    /** List of all Tasks */
    private TaskList tasks;
    /** Ui object to print output to user */
    private Ui ui;

    /**
     * Constructor with no parameters for Launcher class.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("/data", "/data.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Feeds user input to the parser and returns String of Duke's response.
     * @param input Input from user.
     * @return Duke's generated response to user input.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(ui, tasks, storage);
        return parser.parse(input);
    }
}

