package duchess.command;

import duchess.main.Duchess;

/**
 * This class contains the logic to handle the deadline command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class ListCommand extends Command {

    /** Constructs a ListCommand.*/
    public ListCommand() {
        super(null);
    }

    /**
     * Handles the logic for creating an list command.
     * @param duchess The Duchess to return the output to.
     * @return Whether to continue scanning for user input afterwards.
     */
    public boolean handleLogic(Duchess duchess) {
        duchess.getUi().prettyPrint(duchess.getDuchessList().printList());
        return true;
    }
}
