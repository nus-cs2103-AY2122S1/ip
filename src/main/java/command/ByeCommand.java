package command;

import duke.*;

public class ByeCommand extends Command{

    protected ByeCommand(){
        super("");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage store, Duke bot){
        ui.close();
        store.saveToFile(taskList);
        bot.close();
    }
}
