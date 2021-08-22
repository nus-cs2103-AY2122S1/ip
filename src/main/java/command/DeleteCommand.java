package command;

import duke.*;

public class DeleteCommand extends Command{

    protected DeleteCommand(String args){
        super(args);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage store, Duke bot){
        taskList.deleteTask(Integer.valueOf(this.args));
    }
}
