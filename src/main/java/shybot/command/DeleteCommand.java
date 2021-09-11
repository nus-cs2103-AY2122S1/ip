package shybot.command;

import shybot.ShyBot;
import shybot.Storage;
import shybot.exception.ShyBotIndexOutOfRangeException;
import shybot.exception.ShyBotIoException;
import shybot.task.Task;
import shybot.task.TaskList;

/**
 * DeleteCommand class encapsulates command to delete deadline.
 */
public class DeleteCommand extends Command {
    /**
     * Index of the task to be removed.
     */
    private int index;

    /**
     * Constructs an DeleteCommand with the specified index.
     *
     * @param index Index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(ShyBot shybot, TaskList tasks, Storage storage) throws ShyBotIndexOutOfRangeException,
        ShyBotIoException {
        if (index < 1 || index > tasks.toArray().length) {
            throw new ShyBotIndexOutOfRangeException(tasks.toArray().length > 0
                ? "OOPS!!! I'm sorry, index is out of range! " + "Please try with a number from 1 to " + tasks
                .toArray().length
                : "OOPS!!! I'm sorry, there is nothing in the tasks yet.");
        } else {
            Task task = tasks.remove(index - 1, storage);

            String message = "Noted. I've removed this task:\n  " + task + "\nNow you have " + tasks.toArray().length
                + " tasks in the list.";
            shybot.setResponse(message);
        }
    }
}






