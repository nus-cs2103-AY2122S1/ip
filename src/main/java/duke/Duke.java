package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parse.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Gui;
import duke.ui.Ui;
import javafx.application.Application;

/**
 * This is Duke, a program that serves as a ToDo-List.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Duke.
     * Used to initialise Duke.
     *
     * @param filePath String representation of the path where Duke will be run from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Returns the start-up message.
     * If there is saved tasks, display the tasks. Otherwise, start a new ToDo List.
     *
     * @return the start-up message.
     */
    public String startUp() {
        if (taskList.isEmpty()) {
            return "No previous data found.\nLet's start a new To-Do List!";
        } else {
            String str = ui.greetExistingUser();
            str += ui.displayListUi(taskList);
            return str;
        }
    }

    public String getResponse(String input) {
        return run(input);
    }

    /**
     * Runs the command given by the user.
     *
     * @return String representation of the program's respond to the given command.
     */
    public String run(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return ui.displayDukeExceptionMessage(e);
        }
    }

    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
