package duke.commands;

import duke.utils.*;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList task, Ui ui, Storage storage){
        ui.end();
    }

    @Override
    public boolean isExit(){
        return true;
    }

}
