package duke.command;

import duke.tasks.TaskManager;

public class ListCommand extends Command{

    public ListCommand(TaskManager taskList){
        super(taskList, "");
    }

    public CommandResult execute() throws Exception{
        String message = taskList.getList();

        return new CommandResult(message, false);
    }
}