package duchess.command;

import duchess.main.Duchess;

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
     * @param duchess The Duchess to return the output to.
     * @return Whether to continue scanning for user input afterwards.
     */
    public boolean handleLogic(Duchess duchess) {
        duchess.getUi().printError();
        return true;
    }

}
