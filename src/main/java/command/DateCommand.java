package command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import task.Task;

public class DateCommand extends Command {
    /** The date to be queried. **/
    private final LocalDate date;

    /**
     * A public constructor to initialized the DateCommand.
     *
     * @param date The date to be queried.
     */
    public DateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * A method to execute this DateCommand. When the method is executed,
     * it will scan through the given TaskList to find all the tasks that
     * happen on the given date, and send them to the given Ui.
     *
     * @param taskList The given Duke TaskList.
     * @param ui The given Duke Ui.
     * @param storage The given Duke Storage.
     * @throws DukeException Exception thrown when execute the DateCommand.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> dateResult = new ArrayList<>();
        for (int i = 0; i < taskList.amountOfTasks(); i++) {
            if (taskList.getTask(i).isOnDate(date)) {
                dateResult.add(taskList.getTask(i));
            }
        }

        String[] dateResultString = new String[dateResult.size() + 1];
        dateResultString[0] = "Your schedule on "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.US))
                + " is:";
        for (int i = 0; i < dateResult.size(); i++) {
            dateResultString[i + 1] = "  " + (i + 1) + ". " + dateResult.get(i).toString();
        }
        ui.printMessage(dateResultString);
    }
}
