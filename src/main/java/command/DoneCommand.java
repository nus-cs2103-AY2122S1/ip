package command;

import duke.*;

public class DoneCommand extends Command{

    protected DoneCommand(String args){
        super(args);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store, Duke bot){
        tasklist.doneTask(Integer.valueOf(this.args));
        tasklist.printTasks();
    }
}
