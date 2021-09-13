package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class that specifies the properties
 * of a list command.
 */
public class ListCommand extends Command {

    /**
     * Calls parent class to initialise the
     * list command with description "list".
     * @param desc String description of
     * list.
     */
    public ListCommand(String desc) {
        super(desc);
    }

    /**
     * Indicates whether the program should
     * stop running, and in this is case no.
     * @return boolean that this command
     * should exit the application or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Displays all the task previously stored by user.
     * @param tasks TaskList to update change given by user.
     * @param ui Ui class to display messages to user.
     * @param storage Storage updates each time a command
     * make changes to the existing stored tasks.
     * @return String of all the tasks.
     */
    @Override
    public String execute(
            TaskList tasks,
            Ui ui,
            Storage storage) {
        return ui.displayTaskList(tasks.toString());
    }

}