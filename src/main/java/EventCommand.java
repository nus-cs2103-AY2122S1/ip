import java.time.LocalDateTime;

/**
 * This class contains the logic to handle the event command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class EventCommand extends Command {

    /**
     * Constructs an EventCommand.
     * @param name The description of the Event.
     */
    public EventCommand(String name) {
        super(name);
    }

    /**
     * Handles the logic for checking and creating Event tasks.
     * @param input The user given input.
     * @param duchess The Duchess to return the output to.
     * @return Whether to continue scanning for user input afterwards.
     */
    public boolean handleLogic(String input, Duchess duchess)  {
        String invalidMessage = "The command \"event\" should be followed by " +
                "a task and a date and time, e.g (meeting /at 2/10/2019 2pm-4pm).";
        String taskAndDuration = input.split(" ", 2)[1];
        try {
            if (!taskAndDuration.contains(" /at "))
                throw new DuchessException(invalidMessage);
            String[] taskParts = taskAndDuration.split(" /at ", 2);
            String task = taskParts[0];
            String time = taskParts[1];
            if (!time.contains(" "))
                throw new DuchessException(invalidMessage);
            String[] timeParts = time.split(" ", 2);
            String day = timeParts[0];
            String duration = timeParts[1];
            if (!duration.contains("-"))
                throw new DuchessException(invalidMessage);
            // Valid input
            LocalDateTime[] events = Event.convertStringToDate(day, duration);
            Event event = new Event(task, events[0], events[1]);
            duchess.getDuchessList().add(event);
            int listSize = duchess.getDuchessList().getSize();
            duchess.getUi().prettyPrint("Understood. I've added this task:\n    " + event
                    + "\nYou now have " + listSize
                    + (listSize > 1 ? " tasks in the list." : " task in the list."));
        } catch (DuchessException e) {
            duchess.getUi().prettyPrint(e.getMessage());
        }
        DuchessFileHandler.writeToFile(duchess.getDuchessList());
        return true;
    }
}
