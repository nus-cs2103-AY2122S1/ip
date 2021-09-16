package duke.commands;

import java.util.ArrayList;

import duke.exceptions.InvalidDescriptionException;
import duke.exceptions.NoDescriptionException;
import duke.task.Task;

/**
 * Represents a Find Command.
 */
public class FindCommand extends Command {

    /** Unique command word */
    public static final String COMMAND_WORD = "find";
    private String keyword;

    /**
     * Constructs a Find Command.
     * @param fullCommand User input.
     * @throws NoDescriptionException If there is no description.
     * @throws InvalidDescriptionException If the format is wrong.
     */
    public FindCommand(String fullCommand) throws NoDescriptionException, InvalidDescriptionException {
        if (fullCommand.equals("find")) {
            throw new NoDescriptionException("Please specify a keyword to search up.");
        }

        String[] splitCommand = fullCommand.split(" ", 2);
        keyword = splitCommand[1];
    }

    /**
     * Executes the task and returns a CommandResult.
     *
     * @return CommandResult.
     */
    public CommandResult execute() {
        ArrayList<Task> matchedTasks = tasks.findTasks(keyword);
        String response = "";
        int count = 1;

        if (matchedTasks.size() == 0) {
            response += "No matches!";
        } else {
            for (Task t : matchedTasks) {
                response += (count + ". " + t.toString());
                count += 1;
            }
        }

        return new CommandResult(response);
    }
}
