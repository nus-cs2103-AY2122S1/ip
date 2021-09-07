package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

public class AliasCommand extends Command {
    public AliasCommand() {
        setMainCommand("alias");
    }

    @Override
    public String parse(String input, TaskList taskList) throws DukeException {
        int firstSpace = input.indexOf(' ');

        // No space after the command
        if (firstSpace == -1) {
            throw new DukeException("Please input the original command, and the alias.");
        }

        String data = input.substring(firstSpace).strip();

        String[] inputs = data.split(" ");

        if (inputs.length < 2) {
            throw new DukeException("Please input the original command, and the alias.");
        } else if (inputs.length > 2) {
            throw new DukeException("Please input a one-word alias.");
        }

        if (Commands.findCommand(inputs[1]) != null) {
            throw new DukeException("Alias is already in use!");
        }

        Command command = Commands.findCommand(inputs[0]);

        if (command == null) {
            throw new DukeException("Original command can't be found.");
        }

        return command.addAlias(inputs[1]);
    }
}
