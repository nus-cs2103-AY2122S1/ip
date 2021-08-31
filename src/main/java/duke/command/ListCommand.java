package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String USAGE_TEXT = "Usage: list";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Display taskList
        ui.reply(taskList.toString());
    }
}
