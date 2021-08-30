package duke.command;

import duke.util.Keyword;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Handles the command for done.
 *
 * @author marcuspeh
 * @version A-JavaDoc
 * @since 23 Aug 2021
 */
public class DoneCommand implements Command {
    /** Stores the message entered by the user. */
    private String message;

    /** Constructor for duke.command.DoneCommand.
     *
     * @param message Stores the message entered by the user.
     */
    public DoneCommand(String message) {
        this.message = message;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param taskList duke.main.TaskList to execute the command.
     * @param ui       To interact with the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            taskList.markDone(Integer.parseInt(message.substring(Keyword.DONE.length() + 1)));
        } catch (NumberFormatException e) {
            ui.doneErrorMessage();
        } catch (IndexOutOfBoundsException e) {
            ui.doneIndexErrorMessage();
        }
    }
}
