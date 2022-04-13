package catobot.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import catobot.Storage;
import catobot.exception.BotException;
import catobot.exception.InvalidDateTimeException;
import catobot.exception.InvalidEventException;
import catobot.item.Event;
import catobot.item.TaskList;



/**
 * Represents the command to add an event to tasks.
 */
public class EventCommand extends Command {
    private static final String EVENT_INDICATOR = "/at";

    /** Content of the command. */
    private final String description;
    /** Raw input of the date of event. */
    private final String rawDate;

    /**
     * Constructor for EventCommand.
     *
     * @param content The content of the request.
     */
    protected EventCommand(String content) throws BotException {
        String[] details = Parser.parseMultipleArgument(
                content, CommandType.EVENT, EVENT_INDICATOR, new InvalidEventException());
        String description = details[0].trim();
        String rawDate = details[1].trim();
        this.description = description;
        this.rawDate = rawDate;
    }
    /**
     * Adds an event to a list of tasks.
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
            return tasks.add(Event.of(description, date));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }


}
