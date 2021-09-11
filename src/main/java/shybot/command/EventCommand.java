package shybot.command;

import shybot.ShyBot;
import shybot.Storage;
import shybot.exception.ShyBotException;
import shybot.exception.ShyBotIllegalFormatException;
import shybot.task.Event;
import shybot.task.Task;
import shybot.task.TaskList;

/**
 * EventCommand class encapsulates command to add a new event.
 */
public class EventCommand extends AddCommand {

    /**
     * Constructs an EventCommand with the specified command.
     *
     * @param command Body of the command.
     */
    public EventCommand(String command) {
        super(command);
    }

    @Override
    public void execute(ShyBot shybot, TaskList tasks, Storage storage) throws ShyBotException {
        String[] line = command.split(" /at | /tag ");
        if (line.length < 2) {
            throw new ShyBotIllegalFormatException(
                "☹ OOPS!!! Seems like you have entered a wrong format for an event task. "
                    + "Try this instead: event <command> /at <date>"
            );
        }
        Task task;
        String[] tags = line.length > 2 ? line[2].split(" ") : new String[0];
        task = new Event(line[0], line[1], tags);
        tasks.add(task, storage);

        String message = "Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.toArray().length
            + " task(s) in the list.";
        shybot.setResponse(message);
    }
}
