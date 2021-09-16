package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.List;

public class ListCommand extends Command {
    final static String cmd = "list";
    final static String usage = "list all functions";
    final static String format = "list";

    public ListCommand() {}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks, "list");
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
