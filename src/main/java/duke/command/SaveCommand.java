package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.io.IOException;

public class SaveCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.writeCurrentData(tasks.getTasks());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        ui.displaySaveMessage();
    }
}
