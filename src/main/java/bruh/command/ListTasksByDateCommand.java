package bruh.command;

import bruh.exception.MissingArgumentException;
import bruh.parser.Parser;
import bruh.storage.Storage;
import bruh.task.LocalDateTimeOrString;
import bruh.tasklist.TaskList;
import bruh.ui.Ui;

/**
 * Represents a command to list all tasks which match a specified date.
 */
public class ListTasksByDateCommand extends ListTasksCommand {
    private static final String MISSING_ARG_ERROR_MSG = "Please specify a date & time.";

    private final LocalDateTimeOrString dateTimeOrString;

    /**
     * Constructor for a command to list all tasks which match a specified date.
     *
     * @param input The string input containing the date to be matched.
     * @throws MissingArgumentException if missing arguments are found.
     */
    public ListTasksByDateCommand(String input) throws MissingArgumentException {
        String[] inputs = input.split("/date ", 2);
        Parser.checkMissingArguments(inputs, MISSING_ARG_ERROR_MSG);
        this.dateTimeOrString = new LocalDateTimeOrString(inputs[1]);
    }

    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        return taskList.filterByDateTimeOrString(dateTimeOrString).listTasks();
    }
}
