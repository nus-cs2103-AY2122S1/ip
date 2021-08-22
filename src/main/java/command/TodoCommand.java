package command;

import duke.*;
import task.Task;

public class TodoCommand extends Command{

    protected TodoCommand(String args){
        super(args);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage store, Duke bot){
        taskList.add(Task.createTask("todo", this.args));
    }
}
