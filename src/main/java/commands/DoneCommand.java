package commands;

import tasks.Task;
import bot.Bot;
import bot.Ui;
import exceptions.InvalidTaskException;

public class DoneCommand extends Command {

    @Override
    public void run(Bot bot, String[] args) {
        int index = Integer.parseInt(args[0]) - 1;
        if (index < 0 || index >= bot.taskList.get().size()) {
            throw new InvalidTaskException(Ui.ERROR_SIGNATURE + "This task does not exist in the task list!");
        }
        Task task = bot.taskList.getTaskAt(index);
        task.markDone();
        Ui.print(new String[]{
                "Nice! I've marked this task as done:",
                Ui.TEXT_BLOCK_MARGIN + task.toString()
        });
    }

}
