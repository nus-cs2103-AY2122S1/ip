package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;

public class FindCommand extends Command {
    public FindCommand(String input) {
        super(input);
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showMessage("Here are the matching tasks in your list:");
        ui.showIndentedMessage(tasks.findTasks(input).toString());
        return tasks;
    }
}
