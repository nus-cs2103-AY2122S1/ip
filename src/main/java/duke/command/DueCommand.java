package duke.command;

import duke.task.TaskList;

import duke.util.Ui;
import duke.util.Storage;

public class DueCommand extends Command {
    public DueCommand(String input) {
        super(input);
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks occurring within this time period:");
        ui.showIndentedMessage(tasks.getDueTasks(input).toString());
        return tasks;
    }
}
