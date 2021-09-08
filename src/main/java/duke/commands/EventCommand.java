package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.MissingTimeCommandException;
import duke.exceptions.NoDescriptionException;
import duke.task.Event;
import duke.task.Task;

import java.io.IOException;

/**
 * Represents a To Do Command.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    private Task taskToAdd;

    public static final String MESSAGE_SUCCESS = "Task successfully added: %1$s";

    public EventCommand(String fullCommand) throws NoDescriptionException, MissingTimeCommandException {
        if (fullCommand.equals("deadline")) {
            throw new NoDescriptionException("The description of a deadline cannot be empty.");
        }
        if (!fullCommand.contains("/at")) {
            throw new MissingTimeCommandException("Missing Time Command: add '/at' in the command.");
        }
        String[] splitCommand = fullCommand.split(" ", 2);
        String[] body = splitCommand[1].split(" /at ", 2);
        String desc = body[0];
        String date = body[1];
        taskToAdd = new Event(desc, date);
    }

    @Override
    public CommandResult execute() {
        tasks.add(taskToAdd);
        try {
            storage.rewriteFile(tasks);
            return new CommandResult(String.format(MESSAGE_SUCCESS, taskToAdd));
        } catch (IOException e) {
            return new CommandResult(e.getMessage());
        }
    }

    public Task getTaskToAdd() {
        return taskToAdd;
    }

}
