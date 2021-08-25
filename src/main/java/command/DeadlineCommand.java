package command;

import duke.*;
import task.Task;

public class DeadlineCommand extends Command{

    protected DeadlineCommand(String args){
        super(args);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store, Duke bot){
        tasklist.add(Task.createTask("deadline", this.args));
    }
}
