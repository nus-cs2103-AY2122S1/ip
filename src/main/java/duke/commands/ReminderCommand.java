package duke.commands;

import duke.exceptions.InvalidDescriptionException;
import duke.task.Deadline;
import duke.task.Task;

import java.time.LocalDate;
import java.time.Period;

/**
 * Represents the upcoming Deadlines between the current date and (current date + N days).
 */
public class ReminderCommand extends Command {

    public static final String COMMAND_WORD = "reminder";
    public static final String COMMAND_SUCCESS = "Upcoming deadline(s): \n";
    public static final String COMMAND_NO_MATCH = "No upcoming deadlines";
    public static final int DAYS = 2;

    private final LocalDate currentDate;
    private final LocalDate finalDate;

    /**
     * Constructor for ReminderCommand.
     * @param fullCommand
     */
    public ReminderCommand(String fullCommand) throws InvalidDescriptionException{
        if (!fullCommand.equals(COMMAND_WORD)) {
            throw new InvalidDescriptionException("There should be no description after 'reminder'");
        }
        this.currentDate = LocalDate.now();
        this.finalDate = currentDate.plus(Period.ofDays(DAYS));
    }

    /**
     * Returns a CommandResult with upcoming deadlines.
     * @return
     */
    @Override
    public CommandResult execute() {
        String deadlines = getUpcomingDeadlines();
        if (hasUpcomingDeadlines(deadlines)) {
            return new CommandResult(COMMAND_SUCCESS + deadlines);
        }
        return new CommandResult(COMMAND_NO_MATCH);
    }

    private String getUpcomingDeadlines() {
        String response = "";
        int count = 1;
        for (Task t : tasks.getAll()) {
            if (isWithinRange(t)) {
                response += (count + ". " + t.toString() + "\n");
                count += 1;
            }
        }
        return response;
    }

    private boolean isWithinRange(Task t) {
        if (t instanceof Deadline) {
            LocalDate deadlineDate = ((Deadline) t).getDate();
            if (deadlineDate.isEqual(currentDate) ||
                    deadlineDate.isEqual(finalDate) ||
                    (deadlineDate.isAfter(currentDate) && deadlineDate.isBefore(finalDate))) {
                return true;
            }
        }
        return false;
    }

    private boolean hasUpcomingDeadlines(String deadlines) {
        if (deadlines.length() == 0) {
            return false;
        }
        return true;
    }
}
