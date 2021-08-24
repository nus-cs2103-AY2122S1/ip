package duke.command;

import duke.CommandResult;
import duke.TaskList;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public ExitCommand(TaskList taskList) {
        super(taskList);
    }

    @Override
    public CommandResult execute() {
        return new CommandResult("Exiting now...", true);
    }
}
