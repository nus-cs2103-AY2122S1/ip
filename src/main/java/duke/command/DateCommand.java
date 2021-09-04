package duke.command;

import java.time.LocalDate;

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
        // Initialize counters to track number of tasks, events and deadlines.
        int counter = 0;
        int events = 0;
        int deadlines = 0;

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

        // Parses user input into LocalDate. User input for date will follow "date" command.
        String dateString = this.userInput.substring(UserCommands.DATE.getLength() + 1);

        LocalDate localDate = Parser.toLocalDate(dateString);
        String formattedDateString = Parser.parseLocalDate(localDate);

        StringBuilder datesBuilder = new StringBuilder();
        // finds Deadlines and Events with LocalDate that matches date input from user.
        for (Task task : tasks.getTasks()) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (localDate.equals(deadline.getTime())) {
                    counter++;
                    deadlines++;
                    datesBuilder.append(counter).append(".").append(deadline).append("\n");
                }
            }

            if (task instanceof Event) {
                Event event = (Event) task;
                if (localDate.equals(event.getTime())) {
                    counter++;
                    events++;
                    datesBuilder.append(counter).append(".").append(event).append("\n");
                }
            }
        }

        return ui.getDateListSuccessMessage(formattedDateString,
                counter, deadlines, events, datesBuilder.toString());
    }

    /**
     * Finds and returns String describing tasks falling on user specified date.
     *
     * @param tasks TaskList that command executes upon.
     * @param ui Ui contains enums, response messages and exception messages that command execution will use.
     * @param storage Storage that command executes upon.
     * @return String describing tasks falling on user specified date.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storable storage) {
        try {
            return this.getTaskAtDate(tasks, ui);
        } catch (DukeException dukeException) {
            return dukeException.toString();
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
