package duke.commands;

import java.io.IOException;

import duke.exceptions.InvalidDescriptionException;
import duke.exceptions.MissingTimeCommandException;
import duke.exceptions.NoDescriptionException;
import duke.task.Deadline;
import duke.task.Task;

/**
 * Represents a Deadline Command.
 */
public class DeadlineCommand extends Command {

    /** Unique command word */
    public static final String COMMAND_WORD = "deadline";

    /** Successful execution message */
    public static final String MESSAGE_SUCCESS = "Task successfully added:\n%1$s";

    /** Unsuccessful execution message */
    public static final String MESSAGE_EMPTY_DESCRIPTION = "The description of a event cannot be empty.";

    private Task taskToAdd;

    /**
     * Constructs a Deadline Command.
     *
     * @param fullCommand User input
     * @throws NoDescriptionException If there is no description.
     * @throws MissingTimeCommandException If there is no time.
     * @throws InvalidDescriptionException If the format is wrong.
     */
    public DeadlineCommand(String fullCommand) throws NoDescriptionException,
            MissingTimeCommandException, InvalidDescriptionException {
        if (fullCommand.equals(COMMAND_WORD)) {
            throw new NoDescriptionException(MESSAGE_EMPTY_DESCRIPTION);
        }

        if (!fullCommand.contains("/by")) {
            throw new MissingTimeCommandException("Missing Time Command: add '/by' in the command.");
        }

        String[] splitCommand = fullCommand.split(" ", 2);

        // Remove all white space.
        String trimmedBody = splitCommand[1].replaceAll("\\s+", "");

        // Only /at provided in command
        if (trimmedBody.equals("/by")) {
            throw new NoDescriptionException("Please add a description and time.");
        }

        String[] splitTrimmedBody = trimmedBody.split("/by", 2);
        if (splitTrimmedBody[0].equals("") || splitTrimmedBody[1].equals("")) {
            throw new InvalidDescriptionException("Missing description or time.");
        }

        // If /at is not properly used
        if (!fullCommand.contains(" /by ")) {
            throw new MissingTimeCommandException("Missing Time Command: add spaces before and after '/by'.");
        }

        //Split full command by /at.
        String[] splitBody = splitCommand[1].split(" /by ", 2);
        String desc = splitBody[0];
        String date = splitBody[1];
        taskToAdd = new Deadline(desc, date);
    }

    /**
     * Executes the task and returns a CommandResult.
     *
     * @return CommandResult
     */
    @Override
    public CommandResult execute() {
        tasks.add(taskToAdd);
        try {
            storage.appendToFile(taskToAdd);
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
