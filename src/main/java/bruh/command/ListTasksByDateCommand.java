package bruh.command;

import bruh.exception.MissingArgumentException;
import bruh.parser.Parser;
import bruh.storage.Storage;
import bruh.task.LocalDateTimeOrString;
import bruh.tasklist.TaskList;
import bruh.ui.Ui;

public class ListTasksByDateCommand extends ListTasksCommand {
    private final LocalDateTimeOrString dateTimeOrString;

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
