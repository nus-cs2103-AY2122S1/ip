package duke.command;

import duke.tasks.TaskManager;

public class MarkAsDoneCommand extends Command {

    public MarkAsDoneCommand(TaskManager taskList, String arguments){
        super(taskList, arguments);
    }

    @Override
    public CommandResult execute() throws Exception{
        String message = taskList.markAsDone(arguments);

        return new CommandResult(message, true, taskList);
    }
}