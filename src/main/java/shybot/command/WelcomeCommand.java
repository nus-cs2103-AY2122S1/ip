package shybot.command;

import shybot.ShyBot;
import shybot.Storage;
import shybot.task.TaskList;

/**
 * WelcomeCommand class encapsulates ShyBot's welcome.
 */
public class WelcomeCommand extends Command {
    @Override
    public void execute(ShyBot shybot, TaskList tasks, Storage storage) {
        String message = "Jiii~ \nWhat can I do for you?";
        shybot.setResponse(message);
    }
}
