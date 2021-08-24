package catobot.command;

import catobot.Storage;
import catobot.Ui;
import catobot.exception.*;
import catobot.item.Deadline;
import catobot.item.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private String content;

    protected DeadlineCommand(String content) {
        this.content = content;
    }

    /**
     * Displays the list of tasks.
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