package duke.commands;

import duke.Storage;
import duke.TaskList;

public class DukeLovesYouCommand extends Command {

    public DukeLovesYouCommand(Storage storage, TaskList taskList, String[] strParse) {
        super(storage, taskList, strParse);
    }

    @Override
    public String execute() {
        return ("Oh! Love! Dukewu loves you too! (ღˇωˇ)~♥");
    }
}
