package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * Command executed when user exits the bot.
 *
 * @author Quan Teng Foong
 */
public class SaveAndExitCommand extends Command {

    /**
     * Prints a goodbye message.
     *
     * @param taskList the current list of tasks
     * @param ui the user interface object
     * @param storage the storage object
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        storage.save(taskList);
        return "Nice talking to you, goodbye!";
    }

    /**
     * Sends true to exit from the while loop in Duke.run()
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
