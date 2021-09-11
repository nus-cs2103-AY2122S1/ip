package shybot.command;

import shybot.ShyBot;
import shybot.Storage;
import shybot.exception.ShyBotIndexOutOfRangeException;
import shybot.exception.ShyBotIoException;
import shybot.task.Task;
import shybot.task.TaskList;

/**
 * DoneCommand class encapsulates command to mark a task as done.
 */
public class DoneCommand extends Command {
    /**
     * Index of the task to be marked done.
     */
    private int index;

    /**
     * Constructs an DoneCommand with the specified index.
     *
     * @param index Index of the task to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(ShyBot shybot, TaskList tasks, Storage storage)
        throws ShyBotIndexOutOfRangeException, ShyBotIoException {
        if (index < 1 || index > tasks.toArray().length) {
            throw new ShyBotIndexOutOfRangeException(
                tasks.toArray().length > 0 ? "OOPS!!! I'm sorry, index is out of range! "
                    + "Please try with a number from 1 to " + tasks
                    .toArray().length : "OOPS!!! I'm sorry, there is nothing in the list yet.");
        } else {
            Task task = tasks.get(index - 1);
            tasks.markAsDone(task, storage);

            String message = "Nice! I've marked this task as done:\n  " + task;
            shybot.setResponse(message);
        }
    }
}
