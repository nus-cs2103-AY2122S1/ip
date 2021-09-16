package duke.commands;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDescriptionException;
import duke.exceptions.NoDescriptionException;
import duke.task.Task;

public class DoneCommand extends Command {

    /** Unique command word */
    public static final String COMMAND_WORD = "done";

    /** Successful execution message */
    public static final String MESSAGE_SUCCESS = "Noted. I've marked this as done:\n%1$s";
    private int index;

    /**
     * Constructs a Done Command.
     *
     * @param fullCommand User input.
     * @throws NoDescriptionException If there is no description.
     * @throws InvalidDescriptionException If the format is wrong.
     */
    public DoneCommand(String fullCommand) throws NoDescriptionException, InvalidDescriptionException {
        if (fullCommand.equals("done")) {
            throw new NoDescriptionException("Please specify the task number.");
        }

        String[] splitCommand = fullCommand.split(" ", 2);
        String desc = splitCommand[1];

        try {
            index = Integer.parseInt(desc);
        } catch (NumberFormatException e) {
            throw new InvalidDescriptionException("Please append a task number after 'done'.");
        }

        assert index > 0 : "Index provided is negative";
    }

    /**
     * Executes the task and returns a CommandResult.
     *
     * @return CommandResult
     */
    @Override
    public CommandResult execute() {
        try {
            Task taskToMarkDone = tasks.markAsDone(index);
            storage.rewriteFile(tasks);
            return new CommandResult(String.format(MESSAGE_SUCCESS, taskToMarkDone));
        } catch (DukeException | IOException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
