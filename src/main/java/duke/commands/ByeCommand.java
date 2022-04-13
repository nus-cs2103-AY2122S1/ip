package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class that specifies the properties
 * of a bye command.
 */
public class ByeCommand extends Command {

    /**
     * Calls parent class to initialise the
     * bye command with description "bye".
     * @param desc String description of
     * bye.
     */
    public ByeCommand(String desc) {
        super(desc);
    }

    /**
     * Indicates whether the program should
     * stop running, and in this is case yes.
     * @return boolean that this command
     * should exit the application or not.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Displays the bye message when exiting the program.
     * @param tasks TaskList to update change given by user
     * @param ui Ui class to display messages to user
     * @param storage Storage updates each time a command make changes
     * to the existing stored tasks
     * @return String of executed bye command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.displayByeMessage();
    }
}
