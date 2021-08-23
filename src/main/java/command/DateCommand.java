package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import dukeException.DukeException;
import task.Task;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateCommand extends Command {
    public LocalDate date;

    public DateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> dateResult = new ArrayList<>();
        for (int i = 0; i < taskList.amountOfTasks(); i++) {
            if (taskList.getTask(i).onDate(date)) {
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
