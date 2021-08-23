package command;

import catobot.Storage;
import catobot.Ui;
import exception.BotException;
import exception.EmptyCommandException;
import exception.InvalidCommandException;
import item.Event;
import item.TaskList;

import java.time.LocalDate;

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

        if (!content.contains("/at") || content.split("/at").length < 2) {
            throw new InvalidCommandException(
                    "Oh no, I am not sure when this is happening >.<");
        }
        String[] inputs = content.split("event")[1].trim().split(" /at ");
        if (inputs.length == 1) {
            throw new EmptyCommandException("event");
        }
        String description = inputs[0].trim();
        LocalDate date = LocalDate.parse(inputs[1]);
        Ui.respond(tasks.add(Event.of(description, date)));
    }

}