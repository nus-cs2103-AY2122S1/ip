package duchess.command;

import duchess.main.Duchess;
import duchess.main.DuchessException;
import duchess.main.DuchessFileHandler;
import duchess.task.Deadline;

/**
 * This class contains the logic to handle the deadline command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class DeadlineCommand extends Command {

    /**
     * Constructs a DeadlineCommand.
     * @param name The description of the deadline.
     */
    public DeadlineCommand(String name) {
        super(name);
    }

    /**
     * Handles the logic for checking and creating Deadline tasks.
     * @param duchess The Duchess to return the output to.
     * @return Whether to continue scanning for user input afterwards.
     */
    public boolean handleLogic(Duchess duchess)  {
        String invalidMessage = "The command \"deadline\" should be followed by " +
                "a task and a date and time, e.g (read book /by 11/10/2019 4pm).";
        String taskAndBy = getName().split(" ", 2)[1];
        try {
            if (!taskAndBy.contains(" /by "))
                throw new DuchessException(invalidMessage);
            String[] taskParts = taskAndBy.split(" /by ", 2);
            String checkTask = taskParts[0];
            String checkBy = taskParts[1];
            if (checkBy.equals(""))
                throw new DuchessException(invalidMessage);
            // Valid input
            Deadline deadline = new Deadline(checkTask, Deadline.convertStringToDate(checkBy));
            duchess.getDuchessList().add(deadline);
            int listSize = duchess.getDuchessList().getSize();
            duchess.getUi().prettyPrint("Understood. I've added this task:\n    " + deadline
                    + "\nYou now have " + listSize
                    + (listSize > 1 ? " tasks in the list." : " task in the list."));
            DuchessFileHandler.writeToFile(duchess.getDuchessList());
        } catch (DuchessException e) {
            duchess.getUi().prettyPrint(e.getMessage());
        }
        return true;
    }

}
