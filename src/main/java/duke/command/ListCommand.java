package duke.command;

import duke.commandresult.CommandResult;
import duke.tasklist.TaskList;

/**
 * A command that can signify duke to render the TaskList items.
 */
public class ListCommand extends Command {

    /** Class level constant that signifies the command used to invoke this. */
    public static final String COMMAND_WORD = "list";

    /** Class level constant that represents the action taken by the command. */
    public static final String DESCRIPTION = "List the tasks in your Task List";

    /** Class level constant that represents the format needed to successfully complete command. */
    public static final String FORMAT = COMMAND_WORD;

    /**
     * Constructor that creates ListCommand.
     * @param taskList The TaskList to be given by Duke.
     */
    public ListCommand(TaskList taskList) {
        super(taskList);
    }

    /**
     * Returns the string representation of the command description and format.
     * @return String that represents the command description and format.
     */
    public static String formatAndDescription() {
        return COMMAND_WORD + ": " + DESCRIPTION + "\n" + FORMAT;
    }

    /**
     * Overrides execute() from Command and returns a CommandResult which stores the feedback string
     * to be returned to the UserInterface.
     * @return CommandResult to be rendered by UserInterface.
     */
    @Override
    public CommandResult execute() {
        TaskList taskList = super.getTaskList();
        String feedback = "Here are the tasks in your list:\n" + taskList;
        return new CommandResult(feedback, false);
    }
}
