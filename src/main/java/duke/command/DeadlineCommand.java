package duke.command;

import java.io.IOException;
import java.time.DateTimeException;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
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
        String command = super.getUserInput();
        String[] inputValues = command.split(" ");
        if (inputValues.length == 1) {
            //first check if deadline is empty
            return ui.showError("Error! The description and date/time cannot be empty.");
        } else if (!command.contains("/by")) {
            //check for date separator
            return ui.showError("Invalid deadline format!\n"
                    + "Please ensure you specify your date and time after a \"/by\"\n"
                    + "Eg: deadline Submit Assignment /by 2021-08-29 15:00"
            );
        } else {
            int dateTimeIndex = command.indexOf("/");
            String description = command.substring(inputValues[0].length() + 1, dateTimeIndex).strip();
            String dateTime = command.substring(dateTimeIndex + 3).strip();
            try {
                Task deadline = new Deadline(description, dateTime);
                return taskList.add(deadline, storage);
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
