package duke.command;

import java.io.IOException;
import java.time.DateTimeException;

import duke.exception.DukeException;
import duke.task.Event;
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
    /**
     * Constructor of the EventCommand class.
     *
     * @param userInput A string representing the user's input.
     */
    public EventCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Returns a string representing Duke's response to a "event" command.
     *
     * @param taskList The taskList where all tasks are stored.
     * @param ui An instance of the Ui class that is responsible for Duke's user interactions.
     * @param storage An instance of a the Storage class that saves and loads Duke's data.
     * @return A string representing Duke's reply after executing this command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null;
        assert storage != null;
        String command = super.getUserInput();
        String[] inputValues = command.split(" ");
        if (inputValues.length == 1) {
            //first check for empty events
            return ui.showEmptyFieldError(this);
        } else if (!command.contains("/at")) {
            //check for date separator
            return ui.showInvalidFormatError(this);
        }
        int dateTimeIndex = command.indexOf("/");
        String description = command.substring(inputValues[0].length() + 1, dateTimeIndex).strip();
        String dateTime = command.substring(dateTimeIndex + 3).strip();
        try {
            Event event = new Event(description, dateTime);
            assert !event.getDescription().equals("") : "Description cannot be empty";
            assert !event.getAt().equals("") : "At cannot be empty:";
            return taskList.add(event, ui, storage);
        } catch (DateTimeException exception) {
            return ui.showInvalidDateTimeError();
        } catch (DukeException exception) {
            return ui.showError(exception.getMessage());
        } catch (IOException exception) {
            return ui.showSavingError();
        }
    }
}
