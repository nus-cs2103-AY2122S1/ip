package duke.command;

import duke.tasks.TaskManager;

public abstract class Command {
    protected TaskManager taskList;
    protected String arguments;

    protected Command(TaskManager taskList, String arguments){
        this.taskList = taskList;
        this.arguments = arguments;
    }

    public abstract CommandResult execute() throws Exception;

}