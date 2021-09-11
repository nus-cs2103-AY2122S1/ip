package shybot.command;

import shybot.ShyBot;
import shybot.Storage;
import shybot.exception.ShyBotException;
import shybot.exception.ShyBotIllegalFormatException;
import shybot.task.Deadline;
import shybot.task.Task;
import shybot.task.TaskList;

/**
 * DeadlineCommand class encapsulates command to add a new deadline.
 */
public class DeadlineCommand extends AddCommand {
    /**
     * Constructs an DeadlineCommand with the specified command.
     *
     * @param command Bodu of the command.
     */
    public DeadlineCommand(String command) {
        super(command);
    }

    @Override
    public void execute(ShyBot shybot, TaskList tasks, Storage storage)
        throws ShyBotException {
        String[] line = command.split(" /by | /tag ");
        if (line.length < 2) {
            throw new ShyBotIllegalFormatException(
                "☹ OOPS!!! Seems like you have entered a wrong format for a deadline task. "
                    + "Try this instead: deadline <command> /by <date>"
            );
        }
        Task task;
        String[] tags = line.length > 2 ? line[2].split(" ") : new String[0];
        task = new Deadline(line[0], line[1], tags);
        tasks.add(task, storage);

        String message = "Got it. I've added this task:\n  "
            + task
            + "\nNow you have " + tasks.toArray().length + " task(s) in the list.";
        shybot.setResponse(message);
    }
}
