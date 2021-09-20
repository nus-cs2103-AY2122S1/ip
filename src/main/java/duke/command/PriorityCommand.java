package duke.command;

import duke.tasks.TaskManager;

public class PriorityCommand extends Command {

    public PriorityCommand(TaskManager taskList, String arguments){
        super(taskList, arguments);
    }

    @Override
    public CommandResult execute() throws Exception{
        String message = taskList.markAsHigh(arguments);

        return new CommandResult(message, true, taskList);
    }
}