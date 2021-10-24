package duke;

import java.io.IOException;

/**
 * Command to close the application.
 */
public class SaveCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Statistics stats) throws DukeException {
        try {
            storage.writeToFile(tasks);
            storage.writeStatistics(stats);
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
