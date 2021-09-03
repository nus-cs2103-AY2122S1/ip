package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents calling a bye command.
 */
public class ByeCommand extends Command {

    /**
     * Constructor for ByeCommand.
     */
    public ByeCommand() {
        isExit = true;
    }

    /**
     * Executes the bye command.
     *
     * @param tasks list of tasks.
     * @param ui ui to handle user interaction.
     * @param storage handles reading and writing of data file.
     * @return the appropriate farewell message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks.getTaskList());
            return ui.exit();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
