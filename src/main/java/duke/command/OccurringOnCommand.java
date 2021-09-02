package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command to find tasks occurring on a specified day.
 */
public class OccurringOnCommand extends Command {
    private final LocalDate date;

    /**
     * Returns a new OccurringOnCommand object.
     * @param date The specified date.
     * @throws DukeException If the date entered is invalid.
     */
    public OccurringOnCommand(String date) throws DukeException {
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!! Please enter date in format yyyy-mm-dd");
        }
    }

    /**
     * Executes the command to find tasks occurring on a specified day.
     * @param tasks The list of tasks.
     * @param ui The Ui object.
     * @param storage The Storage object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTasksOnDate(date);
    }

    /**
     * If the command is the exit command.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
