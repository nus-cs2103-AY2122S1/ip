package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static String USAGE_TEXT = "help";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
