package duchess.command;

import duchess.main.DuchessException;
import duchess.main.DuchessFileHandler;
import duchess.main.DuchessList;
import duchess.task.Task;

/**
 * This class contains the logic to handle the delete command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a DeleteCommand.
     * @param name The name of the task to be deleted.
     */
    public DeleteCommand(String name) {
        super(name);
    }

    /**
     * Handles the logic for deleting a task.
     * @param duchessList The DuchessList to read or write tasks to.
     * @return The reply from Duchess to the user.
     */
    public String handleLogic(DuchessList duchessList) {
        String index = getDescription();
        String reply;
        // Parsing a non-numeric string will throw a NumberFormatException
        try {
            if (duchessList.checkWithinRange(Integer.parseInt(index))) {
                // Valid delete task
                Task deletedTask = duchessList.delete(Integer.parseInt(index));
                reply = "Alright. I've removed this task:   \n  " + deletedTask
                        + "\nNow you have " + duchessList.getSize() + " tasks in the list.";
                DuchessFileHandler.writeToFile(duchessList);
            } else {
                // "delete" followed by an integer outside of range of the list
                throw new DuchessException("Apologies, that task does not exist and cannot be deleted.");
            }
        } catch (NumberFormatException | DuchessException e) {
            // "delete" followed by an invalid non-integer string input
            if (e instanceof NumberFormatException) {
                reply = "The command \"delete\" should be followed by an integer.";
            } else {
                reply = e.getMessage();
            }
        }
        assert !reply.isBlank() : "Reply should not be empty.";
        return reply;
    }
}
