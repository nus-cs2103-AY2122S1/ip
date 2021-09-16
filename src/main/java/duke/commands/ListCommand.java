package duke.commands;

import duke.exceptions.InvalidDescriptionException;
import duke.task.Task;

/**
 * Represents a List Command.
 */
public class ListCommand extends Command {

    /** Unique command word */
    public static final String COMMAND_WORD = "list";

    /**
     * Constructs a List Command.
     *
     * @param fullCommand User input.
     * @throws InvalidDescriptionException If the format is wrong.
     */
    public ListCommand(String fullCommand) throws InvalidDescriptionException {
        if (!fullCommand.equals(COMMAND_WORD)) {
            throw new InvalidDescriptionException("There should be no description after 'list'");
        }
    }

    /**
     * Executes the task and returns a CommandResult.
     *
     * @return CommandResult.
     */
    @Override
    public CommandResult execute() {
        int count = 1;
        String response = "";

        for (Task t : tasks.getAll()) {
            response += (count + ". " + t.toString() + "\n");
            count += 1;
        }
        return new CommandResult(response);
    }
}
