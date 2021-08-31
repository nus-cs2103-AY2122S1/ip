package duke.command;

import duke.PrintType;
import duke.Tasks.TaskManager;

public class EmptyCommand extends Command{

    public EmptyCommand(TaskManager taskList) {
        super(taskList, "");
    }

    @Override
    public CommandResult execute() throws Exception {
        return new CommandResult(PrintType.NOT_RECOGNISED_LINE.getPrintType(), false, taskList);
    }
}
