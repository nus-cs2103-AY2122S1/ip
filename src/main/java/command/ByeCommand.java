package command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    protected ByeCommand() {
        super("");
    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage store, Duke bot) {
        ui.close();
        store.saveToFile(tasklist);
        bot.close();
        return "See You Again";
    }
}
