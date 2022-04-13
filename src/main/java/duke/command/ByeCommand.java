package duke.command;

import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;

/**
 * Command for the program to exit
 */
public class ByeCommand extends Command {
    @Override
    public String execute(Tasklist tasks, Storage storage, Ui ui) {
        ui.setLoop();
        return ui.byeResponse();
    }
}
