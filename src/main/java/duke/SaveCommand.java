package duke;

import java.io.IOException;

/**
 * Command to close the application.
 */
public class SaveCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ui.saveMessage();
    }

    @Override
    public boolean isClosed() {
        return true;
    }
}
