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
     * Greets user once and attempt to load data from local storage.
     * Subsequently, an infinite loop is formed to prompt user for input
     * and response printed according to user inputs. Function terminates when
     * "bye" command detected.
     */
    protected void run() {
        try {
            taskList.readFile(storage.loadDataFile());
        } catch (DukeException e) {
            ui.respond(e.getMessage());
        }

        ui.greet();
        boolean exit = false;
        while (!exit) {
            try {
                Command c = Parser.parseInputs(ui.nextCommand());
                c.execute(storage, taskList, ui);
                exit = c.isExit();
                ui.promptNext();
            } catch (DukeException e) {
                ui.respond(e.getMessage());
            }
        }
        ui.respond("Ooooh yeah! Can do!");
    }
}
