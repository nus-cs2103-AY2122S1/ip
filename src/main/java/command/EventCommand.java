package command;

import duke.*;
import task.Task;

public class EventCommand extends Command{

    protected EventCommand(String args){
        super(args);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage store, Duke bot){
        taskList.add(Task.createTask("event", this.args));
    }
}
