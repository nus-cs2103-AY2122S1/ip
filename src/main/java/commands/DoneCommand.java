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
    public String[] run(Bot bot, String[] args) throws InvalidTaskException {
        int index = Integer.parseInt(args[0]) - 1;
        validateArgs(index, bot);

        Task task = bot.getTaskList().getTaskAt(index);
        task.markDone();
        return new String[]{
            "Nice! I've marked this task as done:",
            Ui.TEXT_BLOCK_MARGIN + task.toString()
        };
    }

    /**
     * Validate command arg
     *
     * @param arg command argument
     * @param bot Bot in context
     * @throws InvalidTaskException invalid command task
     */
    public void validateArgs(int arg, Bot bot) throws InvalidTaskException {
        if (arg < 0 || arg >= bot.getTaskList().size()) {
            throw new InvalidTaskException(Ui.ERROR_SIGNATURE + "This task does not exist in the task list!");
        }
    }


}
