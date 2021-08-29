package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String USAGE_TEXT = "Usage: list";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Show list
        String lst_display = "\n";

        for (int i = 0; i < taskList.size(); i++) {
            lst_display = lst_display + String.format("\t%d. %s\n", i + 1, taskList.getTask(i));
        }
        ui.reply(lst_display);
    }
}
