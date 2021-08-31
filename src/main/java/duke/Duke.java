package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * A personal assistance bot that tracks Events, Dealines, and todos
 * which can be completed or deleted. A user sends instructions to Duke
 * by typing command sentences.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor initialise storage, task list and ui instances to be used
     * when duke is running.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
    }

    /**
     * Gets a response from duke based on user input message.
     * @param input user instructions for duke in form of string.
     * @return string derived from executing a command or exception message
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parseInputs(input);
            String resp = c.execute(storage, taskList, ui);
            return "Duke says: " + resp + "\n" + ui.promptNext();
        } catch (DukeException e) {
            return ui.respond(e.getMessage());
        }
    }

    /**
     * Greets user once and attempt to load data from local storage.
     * and response printed according to user inputs.
     *
     * @return A string to indicate if duke has been initialised.
     */
    protected String initDuke() {
        try {
            taskList.readFile(storage.loadDataFile());
            return ui.greet();
        } catch (DukeException e) {
            return ui.respond(e.getMessage());
        }
    }
}
