package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * OnCommand class handles the 'on' command to list the tasks on a date.
 */
public class OnCommand extends Command {

    private LocalDate date;

    /**
     * Constructs the OnCommand object.
     *
     * @param date Date queried for the 'on' command.
     */
    public OnCommand(LocalDate date) {
        this.date = date;
    }
    /**
     * Returns the tasks the with same date in a response string.
     *
     * @param taskList The TaskList of Duke.
     * @param storage The Storage of Duke.
     * @return Response string.
     * @throws DukeException  If error occur during execution.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String message = "The tasks that you have on " + date.format(formatter) + " are:\n";
        return message + taskList.checkForDate(date);
    }
}
