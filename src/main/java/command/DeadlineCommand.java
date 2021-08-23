package command;

import catobot.Storage;
import catobot.Ui;
import exception.BotException;
import exception.EmptyCommandException;
import exception.InvalidCommandException;
import item.Deadline;
import item.TaskList;

import java.time.LocalDate;

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
        if (!content.contains("/by") || content.split("/by").length < 2) {
            throw new InvalidCommandException(
                    "Don't cheat me, give me a due time so I can watch you >.<");
        }
        String[] inputs = content.split("deadline")[1].trim().split(" /by ");
        if (inputs.length == 1) {
            throw new EmptyCommandException("deadline");
        }
        String description = inputs[0].trim();
        LocalDate date = LocalDate.parse(inputs[1]);
        Ui.respond(tasks.add(Deadline.of(description, date)));
    }

}