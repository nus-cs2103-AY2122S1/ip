package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A class to handle clean up operations on exiting the program
 */
public class ExitCommand extends Command {
    /**
     * Initializes an instance of ExitCommand class.
     * @param type Type of command (Exit)
     */
    public ExitCommand(String type) {
        super(type);
    }

    /**
     * Executes clean up operations on exiting the program.
     * @param taskList The task list
     * @param ui An object to handle i/o operations through screen
     * @param storage An object to read from and write to storage file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        setExit();
        ui.exit();
    }
}
