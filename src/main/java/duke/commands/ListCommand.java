package duke.commands;

import duke.exceptions.InvalidDescriptionException;
import duke.task.Task;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public ListCommand(String fullCommand) throws InvalidDescriptionException {
        if (!fullCommand.equals(COMMAND_WORD)) {
            throw new InvalidDescriptionException("There should be no description after 'list'");
        }
    }

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
