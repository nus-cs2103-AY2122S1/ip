package duke.command;

import duke.util.Keyword;
import duke.util.Message;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Handles the command for new event.
 *
 * @author marcuspeh
 * @version A-JavaDoc
 * @since 23 Aug 2021
 */
public class EventCommand implements Command {
    /** Stores the message entered by the user. */
    private String message;

    /** Constructor for duke.command.EventCommand.
     *
     * @param message Stores the message entered by the user.
     */
    public EventCommand(String message) {
        this.message = message;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param taskList duke.main.TaskList to execute the command.
     * @param ui To interact with the user.
     */
    @Override
    public Message execute(TaskList taskList, Ui ui) {
        try {
            String[] details = message.split(Keyword.EVENTS.getSeparator());
            return taskList.addEvent(details[0].substring(Keyword.EVENTS.length() + 1), details[1]);
        } catch (IndexOutOfBoundsException e) {
            return ui.formatEventErrorMessage();
        }
    }
}
