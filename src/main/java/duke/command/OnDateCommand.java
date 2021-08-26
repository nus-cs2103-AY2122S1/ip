package duke.command;

import duke.util.Ui;
import duke.util.Storage;

import duke.task.TaskList;

public class OnDateCommand extends Command {
    public OnDateCommand(String input) {
        super(input);
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks occurring on this date:");
        ui.showIndentedMessage(tasks.getOnDateTasks(input).toString());
        return tasks;
    }
}
