package catobot.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import catobot.Storage;
import catobot.exception.BotException;
import catobot.exception.EmptyCommandException;
import catobot.exception.InvalidDateTimeException;
import catobot.exception.InvalidDeadlineException;
import catobot.item.Deadline;
import catobot.item.TaskList;

/**
 * Represents the commands to add a deadline.
 */
public class DeadlineCommand extends Command {
    /** Content of the command. */
    private final String content;

    /**
     * Constructor for a deadline command.
     *
     * @param content The content of the command.
     */
    protected DeadlineCommand(String content) {
        this.content = content;
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
        // If the command is empty
        if (content.split(" ").length == 1) {
            throw new EmptyCommandException("deadline");
        }
        String rawInputs = content.split("deadline")[1].trim();

        // If the command does not have "/by"
        if (!rawInputs.contains("/by")) {
            throw new InvalidDeadlineException();
        }
        String[] details = rawInputs.split("/by");

        // if there is no description or date
        if (details.length < 2) {
            throw new InvalidDeadlineException();
        }

        String description = details[0].trim();
        String rawDate = details[1].trim();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime date = LocalDateTime.parse(rawDate, formatter);
            return tasks.add(Deadline.of(description, date));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

}
