package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents the robot which has corresponding reaction to the user inputs.
 *
 * @author QIN GUORUI
 */
public class Duke {
    private Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    /**
     * Sets up duke instance dealing with file path.
     *
     * @param filePath The important
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program of duke.
     *
     * @param input The user input.
     * @throws IOException The exception related to store and read tasks.
     */
    public String getResponse(String input) throws IOException {
        String output;
        try {
            Command c = Parser.parse(input);
            assert c != null;
            output = c.execute(tasks, ui, storage);
            return output;
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    public static String showComings() {
            TaskList taskListMonth = tasks.tasksWithinMonthOrDay("month");
            assert taskListMonth != null;
            TaskList taskListDay = tasks.tasksWithinMonthOrDay("day");
            assert taskListDay != null;
            return ui.showComings(taskListMonth, taskListDay);
    }

    public static String showList() {
        return tasks.toString();
    }
}
