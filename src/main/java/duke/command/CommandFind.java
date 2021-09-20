package duke.command;

import duke.TaskList;
import duke.ui.UserInterface;

public class CommandFind extends Command {
    public static final String FIND_NAME = "find";

    private String keyword;

    public CommandFind(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui) {
        TaskList matchingTasks = taskList.match(keyword);
        ui.print(matchingTasks.toString());
    }
}
