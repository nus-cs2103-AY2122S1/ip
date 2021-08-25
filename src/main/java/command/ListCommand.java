package command;

import duke.*;

public class ListCommand extends Command{

    protected ListCommand(){
        super("");
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store, Duke bot){
        tasklist.printTasks();
    }
}
