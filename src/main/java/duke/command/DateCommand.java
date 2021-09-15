package duke.command;

import java.time.LocalDate;

import duke.DukeResponse;
import duke.Parser;
import duke.Storable;
import duke.TaskList;
import duke.Ui;
import duke.Ui.UserCommands;
import duke.exception.DukeException;
import duke.exception.MissingDateException;
import duke.exception.MissingSpaceAfterCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Period;
import duke.task.Task;

/**
 * Represents a command that can be executed to print tasks falling on user specified date.
 */
public class DateCommand extends Command {
    private final String userInput;

    /**
     * Constructor for DateCommand.
     * Creates DateCommand containing user input.
     *
     * @param userInput User's input into Duke chatbot.
     */
    public DateCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Finds and returns String describing tasks falling on user specified date.
     *
     * @param tasks TaskList to search in.
     * @param ui Ui to get enums, response messages and exception messages from.
     * @return String describing tasks falling on user specified date.
     * @throws MissingDateException If user input is missing time input.
     * @throws MissingSpaceAfterCommandException If user input has missing spaces.
     * @throws DukeException If underlying methods or checks fail.
     */
    private String getTaskAtDate(TaskList tasks, Ui ui) throws DukeException {
        int counter = 0;
        int events = 0;
        int deadlines = 0;
        int periods = 0;

        checkDateInputValidity();

        // Parses user input into LocalDate. User input for date will follow "date" command.
        String dateString = this.userInput.substring(UserCommands.DATE.getLength() + 1);
        LocalDate localDate = Parser.dateToLocalDate(dateString);
        String formattedDateString = Parser.parseLocalDate(localDate);

        StringBuilder datesBuilder = new StringBuilder();
        // find tasks with LocalDate that matches date input from user.
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            // Increment by 1 to change index to be 1-based.
            int idx = i + 1;

            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (localDate.equals(deadline.getTime())) {
                    counter++;
                    deadlines++;
                    datesBuilder.append(idx).append(".").append(deadline).append("\n");
                }
            }

            if (task instanceof Event) {
                Event event = (Event) task;
                if (localDate.equals(event.getTime())) {
                    counter++;
                    events++;
                    datesBuilder.append(idx).append(".").append(event).append("\n");
                }
            }

            if (task instanceof Period) {
                Period period = (Period) task;
                if (checkWithinPeriod(localDate, period)) {
                    counter++;
                    periods++;
                    datesBuilder.append(idx).append(".").append(period).append("\n");
                }
            }
        }

        return ui.getDateListSuccessMessage(formattedDateString,
                counter, deadlines, events, periods, datesBuilder.toString());
    }

    /**
     * Checks if localDate is within period represented by Period task.
     *
     * @param localDate Date to be checked.
     * @param period Period task whose time period is to be checked against.
     * @return Boolean indicating if localDate is within period represented by Period task.
     */
    private boolean checkWithinPeriod(LocalDate localDate, Period period) {
        LocalDate[] startEndDates = period.getPeriod();
        LocalDate startDate = startEndDates[0];
        LocalDate endDate = startEndDates[1];

        boolean isLaterThanEqualsStartDate = localDate.compareTo(startDate) >= 0;
        boolean isEarlierThanEqualsEndDate = localDate.compareTo(endDate) <= 0;
        return isLaterThanEqualsStartDate && isEarlierThanEqualsEndDate;
    }

    /**
     * Checks if Date input is of an acceptable format.
     *
     * @throws MissingDateException
     * @throws MissingSpaceAfterCommandException
     */
    private void checkDateInputValidity() throws MissingDateException, MissingSpaceAfterCommandException {
        // Check if anything is provided 1 space after date command.
        if (this.userInput.length() <= (UserCommands.DATE.getLength() + 1)) {
            // If nothing is provided, date to search for is not provided.
            // Unlike other commands, a single character following after command without space is an invalid date.
            throw new MissingDateException();
        }

        // Check for space after date command.
        // This prevents wrong date being read by reminding user to add space.
        if (this.userInput.charAt(UserCommands.DATE.getLength()) != ' ') {
            throw new MissingSpaceAfterCommandException(UserCommands.DATE);
        }
    }


    /**
     * Finds and returns String describing tasks falling on user specified date.
     *
     * @param tasks TaskList that command executes upon.
     * @param ui Ui contains enums, response messages and exception messages that command execution will use.
     * @param storage Storage that command executes upon.
     * @return DukeResponse containing string describing tasks falling on user specified date or error message.
     */
    @Override
    public DukeResponse execute(TaskList tasks, Ui ui, Storable storage) {
        try {
            String output = this.getTaskAtDate(tasks, ui);
            return new DukeResponse(output, false);
        } catch (DukeException dukeException) {
            return new DukeResponse(dukeException.toString(), true);
        }
    }

    /**
     * Indicates whether another object is equals to this DateCommand.
     *
     * @param obj Other object to be compared to.
     * @return A boolean indicating whether the other object is equal to this DateCommand.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DateCommand) {
            DateCommand other = (DateCommand) obj;
            return this.userInput.equals(other.userInput);
        }
        return false;
    }
}
