package command;

import duke.*;

public class DeleteCommand extends Command{

    protected DeleteCommand(String args){
        super(args);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store, Duke bot){
        tasklist.deleteTask(Integer.valueOf(this.args));
    }
}
