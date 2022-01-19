package duchess.command;

import duchess.main.DuchessException;
import duchess.main.DuchessFileHandler;
import duchess.main.DuchessList;
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
     * @param duchessList The DuchessList to read or write tasks to.
     * @return The reply from Duchess to the user.
     */
    public String handleLogic(DuchessList duchessList) {
        String index = getDescription();
        String reply;
        // Parsing a non-numeric string will throw a NumberFormatException
        try {
            if (duchessList.checkWithinRange(Integer.parseInt(index))) {
                // Valid done task
                Task task = duchessList.getTask(Integer.parseInt(index));
                if (task.isDone()) {
                    throw new DuchessException("Oops... This task is already done.");
                }
                task.setDoneStatus(true);
                reply = "Brilliant! I've marked this task as done:   \n  " + task;
                DuchessFileHandler.writeToFile(duchessList);
            } else {
                // "done" followed by an integer outside of range of the list
                throw new DuchessException("Apologies, that task does not exist and cannot be marked as done.");
            }
        } catch (NumberFormatException | DuchessException e) {
            // "done" followed by an invalid non-integer string input
            if (e instanceof NumberFormatException) {
                reply = "The command \"done\" should be followed by an integer.";
            } else {
                reply = e.getMessage();
            }
        }
        assert !reply.isBlank() : "Reply should not be empty.";
        return reply;
    }
}
