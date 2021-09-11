package shybot.command;

import shybot.ShyBot;
import shybot.Storage;
import shybot.task.TaskList;

/**
 * ExitCommand class encapsulates command to exit ShyBot.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(ShyBot shybot, TaskList tasks, Storage storage) {
        String message = "Bye. Hope to see you again soon!";
        shybot.setResponse(message);
    }
}
