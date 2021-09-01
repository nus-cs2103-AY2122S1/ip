package commands;

import bot.Bot;
import bot.Ui;
import exceptions.InvalidTaskException;
import tasks.Task;

/**
 * Command to mark a task as done
 */
public class DoneCommand extends Command {

    @Override
    public String[] run(Bot bot, String[] args) {
        int index = Integer.parseInt(args[0]) - 1;
        if (index < 0 || index >= bot.getTaskList().get().size()) {
            throw new InvalidTaskException(Ui.ERROR_SIGNATURE + "This task does not exist in the task list!");
        }
        Task task = bot.getTaskList().getTaskAt(index);
        task.markDone();
        return new String[]{
            "Nice! I've marked this task as done:",
            Ui.TEXT_BLOCK_MARGIN + task.toString()
        };
    }

}
