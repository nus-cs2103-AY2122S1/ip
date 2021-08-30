package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import java.io.IOException;
import java.time.DateTimeException;

/**
 * Represents how Duke responds to a "deadline" command.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class DeadlineCommand extends Command {

    /**
     * Returns a boolean that tells Duke if this is the command to exit.
     *
     * @return A boolean representing the exit condition.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes a "deadline" command.
     *
     * @param taskList The taskList where all tasks are stored.
     * @param ui An instance of the Ui class that is responsible for Duke's user interactions.
     * @param storage An instance of a the Storage class that saves and loads Duke's data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String command = ui.getCommand();
        String[] inputValues = command.split(" ");
        if (inputValues.length == 1) {
            //first check if deadline is empty
            ui.showError("     Error! The description and date/time cannot be empty.");
        } else if (!command.contains("/by")) {
            //check for date separator
            ui.showError("     Invalid deadline format!\n"
                    + "     Please ensure you specify your date and time after a \"/by\"\n"
                    + "     Eg: deadline Submit Assignment /by 2021-08-29 15:00"
            );
        } else {
            int dateTimeIndex = command.indexOf("/");
            String description = command.substring(inputValues[0].length() + 1, dateTimeIndex).strip();
            String dateTime = command.substring(dateTimeIndex + 3).strip();
            try {
                Task deadline = new Deadline(description, dateTime);
                taskList.add(deadline);
                storage.save(taskList);
            } catch (DateTimeException exception) {
                ui.showError(
                        "     Error! Ensure your date and time is valid and formatted correctly!\n"
                        + "     Date: \"YYYY-MM-DD\" format, eg: 2021-08-23\n"
                        + "     Time: 24Hr format, \"HH:mm\", eg: 18:00");
            } catch (DukeException exception) {
                ui.showError(exception.getMessage());
            } catch (IOException exception) {
                ui.showSavingError();
            }

        }
    }
}
