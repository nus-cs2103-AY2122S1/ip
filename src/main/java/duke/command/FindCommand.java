package duke.command;

import duke.util.Keyword;
import duke.util.Message;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Handles the command for finding event.
 *
 * @author marcuspeh
 * @version A-JavaDoc
 * @since 23 Aug 2021
 */
public class FindCommand implements Command {
    /** Stores the message entered by the user. */
    private String message;

    /** Constructor for duke.command.FindCommand.
     *
     * @param message Stores the message entered by the user.
     */
    public FindCommand(String message) {
        this.message = message;
    }

    /**
     * Finds all the task containing the keywords.
     *
     * @param taskList duke.main.TaskList to execute the command.
     * @param ui       To interact with the user.
     * @return message to be used by either the graphic UI or command line UI.
     */
    @Override
    public Message execute(TaskList taskList, Ui ui) {
        try {
            return taskList.findTask(message.substring(Keyword.TODOS.length() + 1));
        } catch (IndexOutOfBoundsException e) {
            return ui.formatFindIndexErrorMessage();
        }
    }
}

