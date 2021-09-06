package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class ErrorCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        System.out.println("Invalid input");
    }

    @Override
    public boolean isExit(){
        return false;
    }

}
