package duke.command;

import duke.tasks.TaskManager;

public class FindCommand extends Command{
    public FindCommand(TaskManager taskList, String arguments){
        super(taskList, arguments);
    }

    @Override
    public CommandResult execute() throws Exception{
        String message = taskList.findTask(arguments);

        return new CommandResult(message, false);
    }


}
