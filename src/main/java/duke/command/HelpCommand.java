package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class HelpCommand extends Command {
    final static String cmd = "help";
    final static String usage = "shows list of all commands & usages";
    final static String format = "help";

    public HelpCommand() {}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showAfterHelp();
    }

    public static String getCmd() {
        return cmd;
    }

    public static String getUsage() {
        return usage;
    }

    public static String getFormat() {
        return format;
    }
}
