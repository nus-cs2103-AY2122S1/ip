package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.task.TaskList;

/**
 * Represents an exit command. An <code>ExitCommand</code> exits
 * the system when a user types bye to Duke.
 */
public class ExitCommand extends Command {
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    public ExitCommand(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return true if it is.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns the format on how to use the command.
     *
     * @return String representation of the help message.
     */
    @Override
    public String getUsageMessage() {
        return "bye | quit the chat bot";
    }

    /**
     * Prints Duke's exit message.
     */
    @Override
    public String execute() {
        return EXIT_MESSAGE;
    }

}
