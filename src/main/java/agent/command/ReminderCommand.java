package agent.command;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import agent.messages.DeadlineReminderMessage;
import agent.tasks.Deadline;
import agent.tasks.TaskList;

/**
 * Handles reminding the user of upcoming deadlines for the specified time period.
 * <p>
 * There are 3 time periods to filter deadlines by, all which is by default, due in a week and
 * due by today.
 *
 * @author kevin9foong
 */
public class ReminderCommand extends Command {

    /**
     * Constructs an instance of <code>ReminderCommand</code> which when executes lists deadlines that are
     * due according to user's specified time frame.
     *
     * @param userInputBody specifies desired time frame to limit due dates to. Include "week" or "today" to specify
     *                      a specific time frame.
     */
    public ReminderCommand(String userInputBody) {
        super(userInputBody);
    }

    /**
     * Filters tasks for undone deadlines which are due before user's specified search time frame and
     * returns chat bot response to be displayed to user which lists the filtered upcoming deadlines.
     *
     * @param taskList handles task operations including adding, deleting, marking as done and retrieval.
     * @return response message which lists upcoming incomplete deadlines within user's specified time frame.
     */
    @Override
    public String execute(TaskList taskList) {
        String userInput = super.getUserInputBody();
        String timeScope = "All";
        List<Deadline> deadlines = taskList.getAllTasks().stream().filter(t -> !t.getIsDone() && t instanceof Deadline)
                .map(Deadline.class::cast).sorted((Comparator.comparing(Deadline::getDueDate))).collect(toList());

        if (userInput != null) {
            if (userInput.contains("week")) {
                deadlines = deadlines.stream().filter(deadline -> deadline.getDueDate()
                        .compareTo(LocalDate.now().plusWeeks(1)) < 0).collect(toList());
                timeScope = "Due within a week from today or earlier";
            } else if (userInput.contains("today")) {
                deadlines = deadlines.stream().filter(deadline -> deadline.getDueDate()
                        .compareTo(LocalDate.now().plusDays(1)) < 0).collect(toList());
                timeScope = "Due today or earlier";
            }
        }

        return new DeadlineReminderMessage(deadlines, timeScope).toString();
    }

    /**
     * Returns false to indicate program should not terminate after command is executed.
     *
     * @return false to indicate program should not terminate after command is executed.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
