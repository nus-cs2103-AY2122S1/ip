package duke.command;

import duke.Storage;
import duke.Ui;

import duke.task.TaskList;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static String USAGE_TEXT = "help";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
