package duke.commands;

import java.io.IOException;

import duke.exceptions.InvalidDescriptionException;
import duke.exceptions.MissingTimeCommandException;
import duke.exceptions.NoDescriptionException;
import duke.task.Event;
import duke.task.Task;

/**
 * Represents an Event Command.
 */
public class EventCommand extends Command {

    /** Unique command word */
    public static final String COMMAND_WORD = "event";

    /** Successful execution message */
    public static final String MESSAGE_SUCCESS = "Task successfully added: %1$s";

    /** Unsuccessful execution message */
    public static final String MESSAGE_EMPTY_DESCRIPTION = "The description of a event cannot be empty.";

    private Task taskToAdd;

    /**
     * Constructs a Event Command.
     *
     * @param fullCommand User input.
     * @throws NoDescriptionException If there is no description.
     * @throws MissingTimeCommandException If there is no time.
     * @throws InvalidDescriptionException If the format is wrong.
     */
    public EventCommand(String fullCommand) throws NoDescriptionException,
            MissingTimeCommandException, InvalidDescriptionException {
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

        //If no errors, create a new Event.
        String[] splitBody = splitCommand[1].split(" /at ", 2);
        String desc = splitBody[0];
        String date = splitBody[1];
        taskToAdd = new Event(desc, date);
    }

    /**
     * Executes the task and returns a CommandResult.
     *
     * @return CommandResult.
     */
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

    /**
     * Returns the task to be added.
     *
     * @return Task.
     */
    public Task getTaskToAdd() {
        return taskToAdd;
    }

}
