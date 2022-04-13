package duke.command;

import duke.util.Keyword;
import duke.util.Message;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Handles the command for new deadline.
 *
 * @author marcuspeh
 * @version A-Assertions
 * @since 6 Sep 2021
 */
public class DeadlineCommand implements Command {
    /** Stores the message entered by the user. */
    private String message;

    /** Constructor for duke.command.DeadlineCommand.
     *
     * @param message Stores the message entered by the user.
     */
    public DeadlineCommand(String message) {
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
        assert taskList != null : " Tasklist is required by command.";
        assert ui != null : " Ui is required by command.";

        try {
            String[] details = message.split(Keyword.DEADLINE.getSeparator());
            assert details.length == 2 : "Deadline task is in the following format 'deadline <task> /by <date time>'";
            assert details[0].length() > Keyword.DEADLINE.length() : "Deadline requires a description";
            return taskList.addDeadline(details[0].substring(Keyword.DEADLINE.length() + 1), details[1]);
        } catch (IndexOutOfBoundsException e) {
            return ui.formatDeadlineErrorMessage();
        }
    }
}
