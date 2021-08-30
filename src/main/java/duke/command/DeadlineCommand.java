package duke.command;

import duke.util.Keyword;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Handles the command for new deadline.
 *
 * @author marcuspeh
 * @version A-JavaDoc
 * @since 23 Aug 2021
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
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            String[] details = message.split(Keyword.DEADLINE.getSeparator());
            taskList.addDeadline(details[0].substring(Keyword.DEADLINE.length() + 1), details[1]);
        } catch (IndexOutOfBoundsException e) {
            ui.deadlineErrorMessage();
        }
    }
}
