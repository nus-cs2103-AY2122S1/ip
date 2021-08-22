package command;

import duke.*;

public class ListCommand extends Command{

    protected ListCommand(){
        super("");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage store, Duke bot){
        taskList.printTasks();
    }
}
