package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ByeCommand extends Command {
    final static String cmd = "bye";
    final static String usage = "exit app";
    final static String format = "bye";

    public ByeCommand() {}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
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
