package duke.commands;

import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;

/**
 * Class that contains the help command
 *
 */
public class HelpCommand extends Command {

    /**
     * Constructor for help command
     *
     */
    public HelpCommand(){}

    /**
     * Executes the help command
     *
     * @param tasklist The list of tasks that is associated with the instance of Duke
     * @param ui The UI that is associated with the instance of Duke
     * @param storage The storage that is associated with the instance of Duke
     * @return
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, DukeStorage storage) {
        return ui.helpMessage();
    }
}
