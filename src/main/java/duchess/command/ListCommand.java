package duchess.command;

import duchess.main.DuchessList;

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
     * @param duchessList The DuchessList to read or write tasks to.
     * @return The reply from Duchess to the user.
     */
    public String handleLogic(DuchessList duchessList) {
        String reply = duchessList.printList();
        assert !reply.isBlank() : "Reply should not be empty.";
        return reply;
    }
}
