package duke;

import command.Command;
import exception.DukeException;
import exception.DukeExceptionType;

import java.time.format.DateTimeParseException;

/**
 * Represents the main Duke program. Running this class's main function executes the program.
 */
public class Duke {

    private final Ui ui;
    private final TaskList taskList;

    /**
     * Default constructor for the Duke program.
     * Uses filepath data/duke.txt by default.
     */
    public Duke() {
        ui = new Ui();
        Storage storage = new Storage("data", "duke.txt");
        taskList = new TaskList(storage);
    }

    /**
     * Runs Duke logic to return response to user input on GUI.
     *
     * @param input User input from the GUI.
     * @return Duke's response on the GUI.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input, taskList);
            return c.execute(taskList);

        } catch (DukeException e) {
            return ui.showException(e);

        } catch (NumberFormatException e) {
            return ui.showException(new DukeException(DukeExceptionType.INVALID_TASK_INDEX));

        } catch (DateTimeParseException e) {
            return ui.showException(new DukeException(DukeExceptionType.INVALID_DATETIME));
        }
    }

    /**
     * Returns Duke program closing message.
     *
     * @return Duke program closing message string.
     */
    String byeMessage() {
        return "Bye! Program closing...";
    }
}
