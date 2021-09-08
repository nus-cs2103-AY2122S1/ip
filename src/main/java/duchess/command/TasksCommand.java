package duchess.command;

import java.time.LocalDateTime;

import duchess.main.DuchessException;
import duchess.main.DuchessList;
import duchess.task.Task;

/**
 * This class contains the logic to handle the tasks command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class TasksCommand extends Command {

    /** The message on the usage of the tasks command. */
    private static final String INVALID_MESSAGE = "The command \"tasks\" should be followed by "
            + "a keyword \"/after\" or \"/before\", a date and/or a time (e.g before 2/10/2019 2pm"
            + " or after today)";

    public TasksCommand(String name) {
        super(name);
    }

    /**
     * Handles the logic for checking tasks before or after a date.
     * @param duchessList The DuchessList to read or write tasks to.
     * @return The reply from Duchess to the user.
     */
    public String handleLogic(DuchessList duchessList) {
        String reply;
        try {
            String[] timeSplit = getDescription().substring(1).split(" ", 2);
            String keyword = timeSplit[0];
            String date = timeSplit[1];
            boolean isBefore = checkKeyword(keyword);
            LocalDateTime dateAndTime = date.equals("today") ? LocalDateTime.now() : Task.convertStringToDate(date);
            String tasksToPrint = filterTasks(duchessList, isBefore, dateAndTime);
            reply = tasksToPrint.isBlank() ? "You have no tasks " + keyword + " " + date
                    : tasksToPrint;
        } catch (ArrayIndexOutOfBoundsException | DuchessException e) {
            if (e instanceof ArrayIndexOutOfBoundsException) {
                reply = INVALID_MESSAGE;
            } else {
                reply = e.getMessage();
            }
        }
        assert !reply.isBlank() : "Reply should not be empty.";
        return reply;
    }

    /**
     * Checks the given keyword if it matches before or after.
     * @param keyword The given keyword.
     * @return True if keyword is before, false if keyword is after.
     * @throws DuchessException If keyword is neither before nor after.
     */
    public boolean checkKeyword(String keyword) throws DuchessException {
        if (keyword.equals("before")) {
            return true;
        } else if (keyword.equals("after")) {
            return false;
        } else {
            throw new DuchessException(INVALID_MESSAGE);
        }
    }

    /**
     * Filters the tasks in the DuchessList before or after a certain time.
     * @param duchessList The DuchessList to check from.
     * @param isBefore Whether to search before or after a certain time.
     * @param dateAndTime The time to search from.
     * @return The tasks which match the specified condition.
     */
    public String filterTasks(DuchessList duchessList, boolean isBefore, LocalDateTime dateAndTime) {
        String tasksToPrint = "";
        for (int i = 1; i < duchessList.getSize() + 1; i++) {
            Task t = duchessList.getTask(i);
            if (isBefore && t.getDateTimeStart().isBefore(dateAndTime)) {
                tasksToPrint += t + "\n";
            } else if (!isBefore && t.getDateTimeStart().isAfter(dateAndTime)) {
                tasksToPrint += t + "\n";
            }
        }
        return tasksToPrint;
    }
}
