package duke.command;

import java.io.IOException;
import java.time.DateTimeException;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents how Duke responds to a "deadline" command.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class DeadlineCommand extends Command {
    /**
     * Constructor of the DeadlineCommand class.
     *
     * @param userInput A string representing the user's input.
     */
    public DeadlineCommand(String userInput) {
        super(userInput);
    }

    /**
     * Returns a string representing Duke's response to a "deadline" command.
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
            //first check if deadline is empty
            return ui.showEmptyFieldError(this);
        } else if (!command.contains("/by")) {
            //check for date separator
            return ui.showInvalidFormatError(this);
        }
        int dateTimeIndex = command.indexOf("/");
        String description = command.substring(inputValues[0].length() + 1, dateTimeIndex).strip();
        String dateTime = command.substring(dateTimeIndex + 3).strip();
        try {
            Deadline deadline = new Deadline(description, dateTime);
            assert !deadline.getDescription().equals("");
            assert !deadline.getBy().equals("");
            return taskList.add(deadline, ui, storage);
        } catch (DateTimeException exception) {
            return ui.showInvalidDateTimeError();
        } catch (DukeException exception) {
            return ui.showError(exception.getMessage());
        } catch (IOException exception) {
            return ui.showSavingError();
        }
    }
}
