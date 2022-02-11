package daisy.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import daisy.task.Storage;
import daisy.task.TaskList;

/**
 * OnCommand class handles the 'on' command to list the tasks on a date.
 */
public class OnCommand extends Command {

    static final String ON_HEADER = "The tasks that you have on %s are:\n";
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
     * @param taskList The TaskList of Daisy.
     * @param storage The Storage of Daisy.
     * @return Response string.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String message = String.format(ON_HEADER, date.format(formatter));
        return message + taskList.checkForDate(date);
    }
}
