package duke.command;

import duke.commandresult.CommandResult;
import duke.tasklist.TaskList;

/**
 * A command that can signify duke to exit.
 */
public class ExitCommand extends Command {

    /** Class level constant that signifies the command used to invoke this. */
    public static final String COMMAND_WORD = "bye";

    /** Class level constant that represents the action taken by the command. */
    public static final String DESCRIPTION = "Exit Duke";

    /** Class level constant that represents the format needed to successfully complete command. */
    public static final String FORMAT = COMMAND_WORD;

    /**
     * Constructor that creates ExitCommand.
     * @param taskList The TaskList to be given by Duke.
     */
    public ExitCommand(TaskList taskList) {
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
     * and the isExit flag to be returned to the UserInterface. This will tell the UserInterface to start
     * exiting and get Duke to save any changes to the TaskList.
     * @return CommandResult to be rendered by UserInterface.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult("Exiting now...", true);
    }
}
