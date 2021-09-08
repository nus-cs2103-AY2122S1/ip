package duke.command;

import duke.error.DukeException;
import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;

public class RevertCommand extends Command {
    @Override
    public String execute(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        Revertible histCom = tasks.popHistory();
        return histCom.revert(tasks, storage, ui);
    }
}
