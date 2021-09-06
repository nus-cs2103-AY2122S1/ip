package duke.commands;

import duke.utils.*;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        for(int i = 0; i < taskList.numberOfTasks(); i++){
            System.out.println((i+1) + "." + taskList.getTask(i).toString());
        }

    }

    @Override
    public boolean isExit(){
        return false;
    }

}
