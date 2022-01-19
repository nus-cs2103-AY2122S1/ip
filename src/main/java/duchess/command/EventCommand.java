package duchess.command;

import java.time.LocalDateTime;

import duchess.main.DuchessException;
import duchess.main.DuchessFileHandler;
import duchess.main.DuchessList;
import duchess.task.Event;

/**
 * This class contains the logic to handle the event command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class EventCommand extends Command {

    /** The message on the usage of the event command. */
    private static final String INVALID_MESSAGE = "The command \"event\" should be followed by "
            + "a task and a date and time, e.g (meeting /at 2/10/2019 2pm-4pm).";

    /**
     * Constructs an EventCommand.
     * @param name The description of the Event.
     */
    public EventCommand(String name) {
        super(name);
    }

    /**
     * Handles the logic for checking and creating Event tasks.
     * @param duchessList The DuchessList to read or write tasks to.
     * @return The reply from Duchess to the user.
     */
    public String handleLogic(DuchessList duchessList) {
        String reply;
        String taskAndDuration = getDescription();
        try {
            Event event = buildEvent(taskAndDuration);
            if (duchessList.checkForDuplicates(event)) {
                reply = "This event already exists in your list.";
            } else {
                duchessList.add(event);
                int listSize = duchessList.getSize();
                reply = "Understood. I've added this task:\n    " + event
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
     * Builds an Event if the description is a valid input.
     * @param description The user given input following the event command.
     * @return The generated Event.
     * @throws DuchessException If the input does not follow the Event template.
     */
    public static Event buildEvent(String description) throws DuchessException {
        if (!description.contains(" /at ")) {
            throw new DuchessException(INVALID_MESSAGE);
        }
        String[] taskParts = description.split(" /at ", 2);
        String task = taskParts[0];
        String time = taskParts[1];
        if (!time.contains(" ")) {
            throw new DuchessException(INVALID_MESSAGE);
        }
        String[] timeParts = time.split(" ", 2);
        String day = timeParts[0];
        String duration = timeParts[1];
        if (!duration.contains("-")) {
            throw new DuchessException(INVALID_MESSAGE);
        }
        LocalDateTime[] events = Event.convertStringToDates(day, duration);
        return new Event(task, events[0], events[1]);
    }
}
