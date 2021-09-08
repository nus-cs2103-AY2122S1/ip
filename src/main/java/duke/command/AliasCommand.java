package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Command to add an alias to an existing command.
 */
public class AliasCommand extends Command {
    public AliasCommand() {
        setMainCommand("alias");
    }

    /**
     * Parses the user input for an old command and a new alias.
     * Then adds that alias to the command, and saves the new alias to be used in the future.
     *
     * @param input Full user input.
     * @param taskList The list of tasks.
     * @return The response to inform the user that a new alias has been set.
     * @throws DukeException Any exception caught when executing this command.
     */
    @Override
    public String parse(String input, TaskList taskList) throws DukeException {
        int firstSpace = input.indexOf(' ');

        // No space after the command
        if (firstSpace == -1) {
            throw new DukeException("Please input the original command, and the alias.");
        }

        String data = input.substring(firstSpace).strip();
        String[] inputs = data.split(" ");

        // expecting exactly two words
        if (inputs.length < 2) {
            throw new DukeException("Please input the original command, and the alias.");
        } else if (inputs.length > 2) {
            throw new DukeException("Please input a one-word alias.");
        }

        String oldName = inputs[0];
        String alias = inputs[1];

        Command command = Commands.findCommand(oldName);
        if (command == null) {
            throw new DukeException("Original command can't be found.");
        }

        if (Commands.findCommand(alias) != null) {
            throw new DukeException("Alias is already in use!");
        }

        return command.addAlias(alias);
    }
}
