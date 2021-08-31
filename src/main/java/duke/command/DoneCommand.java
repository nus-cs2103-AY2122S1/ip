package duke.command;

import duke.util.Keyword;
import duke.util.Message;
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
     * @return message to be used by either the graphic UI or command line UI.
     */
    @Override
    public Message execute(TaskList taskList, Ui ui) {
        try {
            return taskList.markDone(Integer.parseInt(message.substring(Keyword.DONE.length() + 1)));
        } catch (NumberFormatException e) {
            return ui.doneErrorMessage();
        } catch (IndexOutOfBoundsException e) {
            return ui.doneIndexErrorMessage();
        }
    }
}
