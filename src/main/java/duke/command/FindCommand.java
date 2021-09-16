package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class FindCommand extends Command {
    final static String cmd = "find";
    final static String usage = "find a task by searching for a keyword";
    final static String format = "find {keyword}";

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public FindCommand() {}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList t = tasks.matchTasks(keyword);
        return ui.showTaskList(t, "find");
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
