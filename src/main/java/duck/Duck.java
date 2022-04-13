package duck;

import duck.command.Command;
import duck.exception.DuckException;
import duck.exception.DuckExceptionType;

import java.time.format.DateTimeParseException;

/**
 * Represents the main Duck program. Running this class's main function executes the program.
 */
public class Duck {

    private final Ui ui;
    private final TaskList taskList;

    /**
     * Default constructor for the Duck program.
     * Uses filepath data/duck.txt by default.
     */
    public Duck() {
        ui = new Ui();
        Storage storage = new Storage("data", "duck.txt");
        taskList = new TaskList(storage);
    }

    /**
     * Runs Duck logic to return response to user input on GUI.
     *
     * @param input User input from the GUI.
     * @return Duck's response on the GUI.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input, taskList);
            return c.execute(taskList);

        } catch (DuckException e) {
            return ui.showException(e);

        } catch (NumberFormatException e) {
            return ui.showException(new DuckException(DuckExceptionType.INVALID_TASK_INDEX));

        } catch (DateTimeParseException e) {
            return ui.showException(new DuckException(DuckExceptionType.INVALID_DATETIME));
        }
    }
}
