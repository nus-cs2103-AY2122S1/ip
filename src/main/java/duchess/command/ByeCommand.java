package duchess.command;

import duchess.main.Duchess;

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
     * @param duchess The Duchess to return the output to.
     * @return Whether to continue scanning for user input afterwards.
     */
    public boolean handleLogic(Duchess duchess) {
        return false;
    }

}
