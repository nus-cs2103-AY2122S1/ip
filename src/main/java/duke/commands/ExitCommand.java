package duke.commands;

import duke.utils.*;

/**
 * Class that is a subclass of Command class
 * and handles the behaviour of the Command for
 * when exiting the program
 */
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
