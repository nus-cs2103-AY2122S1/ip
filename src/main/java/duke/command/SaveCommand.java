package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class SaveCommand extends Command {
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage store) {
        try {
            store.save(tasklist);
            return ui.notifySavingComplete();
        } catch (IOException e) {
            return ui.notifySavingError();
        }

    }
}
