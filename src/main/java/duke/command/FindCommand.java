package duke.command;

import duke.CommandResult;
import duke.DukeException;
import duke.TaskList;

public class FindCommand extends Command {

    private final String searchInput;
    public static final String COMMAND_WORD = "find";

    public FindCommand(TaskList taskList, String searchInput) {
        super(taskList);
        this.searchInput = searchInput;
    }

    @Override
    public CommandResult execute() throws DukeException {
        TaskList taskList = super.getTaskList();
        TaskList filtered = taskList.filter(this.searchInput);
        String feedback = "Here are the matching tasks in your list:\n" + filtered.toString();
        return new CommandResult(feedback, false);
    }
}
