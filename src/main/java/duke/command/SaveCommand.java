package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

import java.io.IOException;

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
