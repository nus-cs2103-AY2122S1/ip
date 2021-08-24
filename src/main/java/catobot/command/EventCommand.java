package catobot.command;

import catobot.Storage;
import catobot.Ui;
import catobot.exception.BotException;
import catobot.exception.EmptyCommandException;
import catobot.exception.InvalidDateException;
import catobot.exception.InvalidEventException;
import catobot.item.Deadline;
import catobot.item.Event;
import catobot.item.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private final String content;

    protected EventCommand(String content) {
        this.content = content;
    }

    /**
     * Displays the list of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        if (content.split(" ").length == 1) {
            throw new EmptyCommandException("event");
        }
        if (!content.contains("/at") || content.split("/at").length < 2) {
            throw new InvalidEventException();
        }
        String[] inputs = content.split("event")[1].trim().split(" /at ");
        String description = inputs[0].trim();
        try {
            LocalDate date = LocalDate.parse(inputs[1]);
            Ui.respond(tasks.add(Event.of(description, date)));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

}