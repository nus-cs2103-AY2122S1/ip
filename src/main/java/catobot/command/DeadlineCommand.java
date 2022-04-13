package catobot.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import catobot.Storage;
import catobot.exception.BotException;
import catobot.exception.InvalidDateTimeException;
import catobot.exception.InvalidDeadlineException;
import catobot.item.Deadline;
import catobot.item.TaskList;

/**
 * Represents the commands to add a deadline.
 */
public class DeadlineCommand extends Command {
    private static final String DEADLINE_INDICATOR = "/by";

    /** Content of the command. */
    private final String description;
    /** Raw input of the date of deadline. */
    private final String rawDate;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param content The content of the request.
     */
    protected DeadlineCommand(String content) throws BotException {
        String[] details = Parser.parseMultipleArgument(
                content, CommandType.DEADLINE, DEADLINE_INDICATOR, new InvalidDeadlineException());
        String description = details[0].trim();
        String rawDate = details[1].trim();
        this.description = description;
        this.rawDate = rawDate;
    }

    /**
     * Adds a deadline to a list of tasks.
     *
     * @param tasks The list of tasks to be worked on.
     * @param storage The storage of the tasks.
     * @return The text to display.
     * @throws BotException If the description is invalid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws BotException {
        try {
            LocalDateTime date = LocalDateTime.parse(rawDate, Command.DATE_FORMAT);
            return tasks.add(Deadline.of(description, date));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }
}
