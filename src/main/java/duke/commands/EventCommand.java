package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.InvalidDescriptionException;
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
    public static final String MESSAGE_EMPTY_DESCRIPTION = "The description of a event cannot be empty.";

    public EventCommand(String fullCommand) throws NoDescriptionException, MissingTimeCommandException, InvalidDescriptionException {
        if (fullCommand.equals(COMMAND_WORD)) {
            throw new NoDescriptionException(MESSAGE_EMPTY_DESCRIPTION);
        }

        if (!fullCommand.contains("/at")) {
            throw new MissingTimeCommandException("Missing Time Command: add '/at' in the command.");
        }

        String[] splitCommand = fullCommand.split(" ", 2);

        // Remove all white space.
        String trimmedBody = splitCommand[1].replaceAll("\\s+", "");

        // Only /at provided in command
        if (trimmedBody.equals("/at")) {
            throw new NoDescriptionException("Please add a description and time.");
        }

        String[] splitTrimmedBody = trimmedBody.split("/at", 2);
        if (splitTrimmedBody[0].equals("") || splitTrimmedBody[1].equals("")) {
            throw new InvalidDescriptionException("Missing description or time.");
        }

        // If /at is not properly used
        if (!fullCommand.contains(" /at ")) {
            throw new MissingTimeCommandException("Missing Time Command: add spaces before and after '/at'.");
        }

        //Split full command by /at.
        String[] splitBody = splitCommand[1].split(" /at ", 2);
        String desc = splitBody[0];
        String date = splitBody[1];
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
