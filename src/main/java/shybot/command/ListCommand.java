package shybot.command;

import shybot.ShyBot;
import shybot.Storage;
import shybot.task.TaskList;

/**
 * ListCommand class encapsulates command to list tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(ShyBot shybot, TaskList tasks, Storage storage) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1 + ". " + tasks.get(i) + "\n");
        }
        String message = sb.toString();
        shybot.setResponse(message);
    }
}
