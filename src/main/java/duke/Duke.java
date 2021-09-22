package duke;

import java.util.ArrayList;

import duke.command.Command;

/**
 * Class used to start ChatBot.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor to initialize Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            taskList = new TaskList(new ArrayList<>());
            ui.showError(e);
        }
    }
    protected String run(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }
}
