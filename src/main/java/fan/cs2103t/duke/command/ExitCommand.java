package fan.cs2103t.duke.command;

import fan.cs2103t.duke.task.TaskList;
import fan.cs2103t.duke.ui.Ui;

/**
 * Represents a Duke's command which shows a goodbye message upon execution.
 * <p>
 * This is a subclass of the <code>Command</code> class.
 */
public class ExitCommand extends Command {

    /**
     * Executes this command. Displays a goodbye message to the user through the specified UI.
     * Returns the goodbye message as a string.
     *
     * @param taskList useless parameter.
     * @param ui the UI for the message to be displayed through.
     * @return the goodbye message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        String output;
        output = ui.dismiss();
        return output;
    }

    /**
     * Checks if the specified command is an exit command.
     *
     * @param command the command to be checked.
     * @return true if the specified command is an instance of <code>ExitCommand</code>.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

}
