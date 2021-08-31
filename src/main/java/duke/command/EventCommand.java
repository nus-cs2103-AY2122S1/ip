package duke.command;

import java.io.IOException;
import java.time.DateTimeException;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents how Duke responds to an "event" command.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class EventCommand extends Command {

    public EventCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes an "event" command.
     *
     * @param taskList The taskList where all tasks are stored.
     * @param ui An instance of the Ui class that is responsible for Duke's user interactions.
     * @param storage An instance of a the Storage class that saves and loads Duke's data.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String command = super.getUserInput();
        String[] inputValues = command.split(" ");
        if (inputValues.length == 1) {
            //first check for empty events
            return ui.showError("Error! The description and date/time cannot be empty.");
        } else if (!command.contains("/at")) {
            //check for date separator
            return ui.showError("Invalid event format!\n"
                    + "Please ensure you specify your date and time after a \"/at\"\n"
                    + "Eg: event Attend physical lessons /at 2021-08-29 15:00");
        } else {
            int dateTimeIndex = command.indexOf("/");
            String description = command.substring(inputValues[0].length() + 1, dateTimeIndex).strip();
            String dateTime = command.substring(dateTimeIndex + 3).strip();

            try {
                Task event = new Event(description, dateTime);
                return taskList.add(event, storage);
            } catch (DateTimeException exception) {
                return ui.showError(
                        "Error! Ensure your date and time is valid and formatted correctly!\n"
                                + "Date: \"YYYY-MM-DD\" format, eg: 2021-08-23\n"
                                + "Time: 24Hr format, \"HH:mm\", eg: 18:00");
            } catch (DukeException exception) {
                return ui.showError(exception.getMessage());
            } catch (IOException exception) {
                return ui.showSavingError();
            }
        }
    }

}
