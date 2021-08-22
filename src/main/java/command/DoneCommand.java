package command;

import duke.*;

public class DoneCommand extends Command{

    protected DoneCommand(String args){
        super(args);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage store, Duke bot){
        taskList.doneTask(Integer.valueOf(this.args));
        taskList.printTasks();
    }
}
