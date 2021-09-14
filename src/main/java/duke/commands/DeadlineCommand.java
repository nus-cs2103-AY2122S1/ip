package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.InvalidDescriptionException;
import duke.exceptions.MissingTimeCommandException;
import duke.exceptions.NoDescriptionException;
import duke.task.Event;
import duke.task.Task;
import duke.task.Deadline;

import java.io.IOException;

/**
 * Represents a To Do Command.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_SUCCESS = "Task successfully added: %1$s";
    public static final String MESSAGE_EMPTY_DESCRIPTION = "The description of a event cannot be empty.";

    private Task taskToAdd;

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
