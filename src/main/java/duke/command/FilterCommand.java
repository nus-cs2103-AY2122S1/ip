package duke.command;

import java.util.List;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This class encapsulates the command dealing with filtering the tasks list using a date/time specified.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class FilterCommand extends Command {
    private static final String ERROR_MISSING_DATE =
            "Please include a date/time for me to search after the filter command!";
    private static final String ERROR_MULTIPLE_DATES =
            "Sorry, I can only search using one date/time.";
    private static final String EMPTY_FILTERED_LIST =
            "There are no tasks on this day.";

    private final TaskList list;

    public FilterCommand(TaskList list) {
        this.list = list;
    }

    @Override
    public String getResponse(String input) {
        if (input.split(" ").length < 2) { // Guard Clause
            return ERROR_MISSING_DATE;
        }

        if (input.split(" ").length > 2) { // Guard Clause
            return ERROR_MULTIPLE_DATES;
        }

        String[] extracted = input.split(" ", 2);
        List<Task> extractedTask = list.filterList(extracted[1]);

        if (extractedTask.size() == 0) {
            return EMPTY_FILTERED_LIST;
        } else {
            return formatFilteredList(extractedTask);
        }
    }

    private String formatFilteredList(List<Task> extractedTask) {
        String output = String.format("Here are your tasks for this day:%s", Ui.LINE_SEPARATOR);

        int count = 1;
        for (Task t : extractedTask) {
            output = output.concat(String.format("%d. %s", count++, t + Ui.LINE_SEPARATOR));
        }

        return output;
    }
}
