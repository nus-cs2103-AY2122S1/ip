package duchess.command;

import duchess.main.Duchess;
import duchess.main.DuchessException;
import duchess.main.DuchessFileHandler;
import duchess.task.Task;

/**
 * This class contains the logic to handle the done command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class DoneCommand extends Command {

    /**
     * Constructs a DoneCommand.
     * @param name The name of the task to be marked as done.
     */
    public DoneCommand(String name) {
        super(name);
    }

    /**
     * Handles the logic for marking a task a done.
     * @param duchess The Duchess to return the output to.
     * @return Whether to continue scanning for user input afterwards.
     */
    public boolean handleLogic(Duchess duchess)  {
        String index = getName().split(" ", 2)[1];
        // Parsing a non-numeric string will throw a NumberFormatException
        try {
            if (duchess.getDuchessList().checkWithinRange(Integer.parseInt(index))) {
                // Valid done task
                Task task = duchess.getDuchessList().getTask(Integer.parseInt(index));
                if (task.isDone())
                    throw new DuchessException("Oops... This task is already done.");
                task.setDone(true);
                duchess.getUi().prettyPrint("Brilliant! I've marked this task as done:   \n  " + task);
                DuchessFileHandler.writeToFile(duchess.getDuchessList());
            } else {
                // "done" followed by an integer outside of range of the list
                throw new DuchessException("Apologies, that task does not exist and cannot be marked as done.");
            }
        } catch (NumberFormatException|DuchessException e) {
            // "done" followed by an invalid non-integer string input
            if (e instanceof NumberFormatException)
                duchess.getUi().prettyPrint("The command \"done\" should be followed by an integer.");
            else
                duchess.getUi().prettyPrint(((DuchessException) e).getMessage());
        }
        return true;
    }
}
