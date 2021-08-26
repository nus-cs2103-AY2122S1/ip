package duke.command;

import duke.exception.MissingArgumentException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.LocalDateTimeOrString;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to list all tasks which match a specified date.
 */
public class ListTasksByDateCommand extends ListTasksCommand {
    private final LocalDateTimeOrString dateTimeOrString;

    /**
     * Constructor for a command to list all tasks which match a specified date.
     *
     * @param input The string input containing the date to be matched.
     * @throws MissingArgumentException if missing arguments are found.
     */
    public ListTasksByDateCommand(String input) throws MissingArgumentException {
        String[] inputs = input.split("/date ", 2);
        Parser.checkMissingArguments(inputs, "Please specify a date & time.");
        this.dateTimeOrString = new LocalDateTimeOrString(inputs[1]);
    }

    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        return taskList.filterByDateTimeOrString(dateTimeOrString).listTasks();
    }
}
