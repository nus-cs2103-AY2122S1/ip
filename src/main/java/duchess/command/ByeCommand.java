package duchess.command;

import duchess.main.DuchessList;

/**
 * This class contains the logic to handle the bye command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand.
     */
    public ByeCommand() {
        super(null);
    }

    /**
     * Handles the logic for exiting the Duchess interface.
     * @param duchessList The DuchessList to read or write tasks to.
     * @return The reply from Duchess to the user.
     */
    public String handleLogic(DuchessList duchessList) {
        return "I bid thee farewell.";
    }
}
