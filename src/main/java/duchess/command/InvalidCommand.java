package duchess.command;

import duchess.main.DuchessList;

/**
 * This class implements the logic to handle invalid commands.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class InvalidCommand extends Command {

    /** Constructs an InvalidCommand.*/
    public InvalidCommand() {
        super(null);
    }

    /**
     * Handles the logic for creating an invalid command.
     * @param duchessList The DuchessList to read or write tasks to.
     * @return The reply from Duchess to the user.
     */
    public String handleLogic(DuchessList duchessList) {
        return "Apologies, I didn't catch that.";
    }
}
