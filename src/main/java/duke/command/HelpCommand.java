package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class HelpCommand extends Command {
    static final String CMD = "help";
    static final String USAGE = "shows list of all commands & usages";
    static final String FORMAT = "help";

    public HelpCommand() {}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showAfterHelp();
    }

    public static String getCmd() {
        return CMD;
    }

    public static String getUsage() {
        return USAGE;
    }

    public static String getFormat() {
        return FORMAT;
    }
}
