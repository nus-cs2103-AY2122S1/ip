package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class FindCommand extends Command {
    static final String CMD = "find";
    static final String USAGE = "find a task by searching for a keyword";
    static final String FORMAT = "find {keyword}";

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
        return CMD;
    }

    public static String getUsage() {
        return USAGE;
    }

    public static String getFormat() {
        return FORMAT;
    }
}
