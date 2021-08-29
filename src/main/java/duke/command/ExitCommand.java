package duke.command;

import duke.CommandResult;
import duke.TaskList;

/**
 * A command that can signify duke to exit.
 */
public class ExitCommand extends Command {

    /** Class level constant that signifies the command used to invoke this. */
    public static final String COMMAND_WORD = "bye";

    /**
     * Constructor that creates ExitCommand.
     * @param taskList The TaskList to be given by Duke.
     */
    public ExitCommand(TaskList taskList) {
        super(taskList);
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
