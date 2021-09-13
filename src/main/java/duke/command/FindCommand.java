package duke.command;

import duke.Storage;
import duke.task.TaskList;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.findTask(keyword);
    }
}
