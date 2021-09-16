package duke.commands;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDescriptionException;
import duke.exceptions.NoDescriptionException;
import duke.task.Task;

/**
 * Represents a Delete Command.
 */
public class DeleteCommand extends Command {

    /** Unique command word */
    public static final String COMMAND_WORD = "delete";

    /** Successful execution message */
    public static final String MESSAGE_SUCCESS = "Noted. I've marked this as done: %1$s";
    private int index;


    /**
     * Constructs a Delete Command.
     *
     * @param fullCommand User input.
     * @throws NoDescriptionException If there is no description.
     * @throws InvalidDescriptionException If the format is wrong.
     */
    public DeleteCommand(String fullCommand) throws NoDescriptionException, InvalidDescriptionException {
        if (fullCommand.equals("delete")) {
            throw new NoDescriptionException("Please specify a task to delete.");
        }

        String[] splitCommand = fullCommand.split(" ", 2);
        String desc = splitCommand[1];

        try {
            index = Integer.parseInt(desc);
        } catch (NumberFormatException e) {
            throw new InvalidDescriptionException("Please append a task number after 'delete'.");
        }

        assert index > 0 : "Index provided is negative";
    }

    /**
     * Executes the task and returns a CommandResult.
     *
     * @return CommandResult
     */
    public CommandResult execute() {
        try {
            Task taskToDelete = tasks.deleteTask(index);
            storage.rewriteFile(tasks);
            return new CommandResult(String.format(MESSAGE_SUCCESS, taskToDelete));
        } catch (DukeException | IOException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
