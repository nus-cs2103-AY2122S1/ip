package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class handles command that stops the programme.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Constructor for ExitCommand.
     */
    public ExitCommand() {
    }


    /**
     * Method to execute command.
     *
     * @param tasks The list of tasks in the current programme.
     * @param ui The user interface.
     * @param storage Handles interaction with the file.
     * @return Exit message.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        storage.save(tasks.getList());
        return ui.exit();
    }
}
