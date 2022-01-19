package duchess.command;

import duchess.main.DuchessException;
import duchess.main.DuchessFileHandler;
import duchess.main.DuchessList;
import duchess.task.Deadline;

/**
 * This class contains the logic to handle the deadline command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class DeadlineCommand extends Command {

    /** The message on the usage of the deadline command. */
    private static final String INVALID_MESSAGE = "The command \"deadline\" should be followed by "
            + "a task and a date and time, e.g (read book /by 11/10/2019 4pm).";

    /**
     * Constructs a DeadlineCommand.
     * @param name The description of the deadline.
     */
    public DeadlineCommand(String name) {
        super(name);
    }

    /**
     * Handles the logic for checking and creating Deadline tasks.
     * @param duchessList The DuchessList to read or write tasks to.
     * @return The reply from Duchess to the user.
     */
    public String handleLogic(DuchessList duchessList) {
        String description = getDescription();
        String reply = "";
        try {
            Deadline deadline = buildDeadline(description);
            if (duchessList.checkForDuplicates(deadline)) {
                reply = "This deadline already exists in your list.";
            } else {
                duchessList.add(deadline);
                int listSize = duchessList.getSize();
                reply = "Understood. I've added this task:\n    " + deadline
                        + "\nYou now have " + listSize
                        + (listSize > 1 ? " tasks in the list." : " task in the list.");
                DuchessFileHandler.writeToFile(duchessList);
            }
        } catch (DuchessException e) {
            reply = e.getMessage();
        }
        assert !reply.isBlank() : "Reply should not be empty.";
        return reply;
    }

    /**
     * Builds a Deadline if the description is a valid input.
     * @param description The user given input following the deadline command.
     * @return The generated Deadline.
     * @throws DuchessException If the input does not follow the Deadline template.
     */
    public static Deadline buildDeadline(String description) throws DuchessException {
        if (!description.contains(" /by ")) {
            throw new DuchessException(INVALID_MESSAGE);
        }
        String[] taskParts = description.split(" /by ", 2);
        String checkTask = taskParts[0];
        String checkBy = taskParts[1];
        if (checkBy.equals("")) {
            throw new DuchessException(INVALID_MESSAGE);
        }
        // Valid input
        return new Deadline(checkTask, Deadline.convertStringToDate(checkBy));
    }
}
