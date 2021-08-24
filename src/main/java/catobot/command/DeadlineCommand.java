package catobot.command;

import catobot.Storage;
import catobot.Ui;
import catobot.exception.BotException;
import catobot.exception.EmptyCommandException;
import catobot.exception.InvalidDateException;
import catobot.exception.InvalidDeadlineException;
import catobot.item.Deadline;
import catobot.item.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
     * @param ui The ui that responds to the user.
     * @param storage The storage of the tasks.
     * @throws BotException If the description is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        if (content.split(" ").length == 1) {
            throw new EmptyCommandException("deadline");
        }
        if (!content.contains("/by") || content.split("/by").length < 2) {
            throw new InvalidDeadlineException();
        }
        String[] inputs = content.split("deadline")[1].trim().split(" /by ");
        String description = inputs[0].trim();
        try {
            LocalDate date = LocalDate.parse(inputs[1]);
            Ui.respond(tasks.add(Deadline.of(description, date)));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

}