package duchess.command;

import duchess.main.Duchess;
import duchess.main.DuchessException;
import duchess.main.DuchessFileHandler;
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
     * @param duchess The Duchess to return the output to.
     * @return Whether to continue scanning for user input afterwards.
     */
    public boolean handleLogic(Duchess duchess)  {
        String index = getName();
        // Parsing a non-numeric string will throw a NumberFormatException
        try {
            if (duchess.getDuchessList().checkWithinRange(Integer.parseInt(index))) {
                // Valid delete task
                Task deletedTask = duchess.getDuchessList().delete(Integer.parseInt(index));
                duchess.getUi().prettyPrint("Alright. I've removed this task:   \n  " + deletedTask
                        + "\nNow you have " + duchess.getDuchessList().getSize() + " tasks in the list.");
                DuchessFileHandler.writeToFile(duchess.getDuchessList());
            } else {
                // "delete" followed by an integer outside of range of the list
                throw new DuchessException("Apologies, that task does not exist and cannot be deleted.");
            }
        } catch (NumberFormatException|DuchessException e) {
            // "delete" followed by an invalid non-integer string input
            if (e instanceof NumberFormatException)
                duchess.getUi().prettyPrint("The command \"delete\" should be followed by an integer.");
            else
                duchess.getUi().prettyPrint(((DuchessException) e).getMessage());
        }
        return true;
    }
}
