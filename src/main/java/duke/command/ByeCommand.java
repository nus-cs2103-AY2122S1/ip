package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;

/** ByeCommand class */
public class ByeCommand extends Command {

    /** Message shown to the user */
    public static final String BYE_MESSAGE = "Bye";

    /**
     * Constructor for a ByeCommand
     * @param userCommand The command the user gives
     * @param userArgument The argument (rest of the String after the command)
     */
    public ByeCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    /**
     * Method to run when the command is executed
     * @return The bye message
     */
    public String execute(TaskList tasks, Storage storage) {
        return BYE_MESSAGE;
    }
}
