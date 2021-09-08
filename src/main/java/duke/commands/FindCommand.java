package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidDescriptionException;
import duke.exceptions.NoDescriptionException;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    String keyword;

    public FindCommand(String fullCommand) throws NoDescriptionException, InvalidDescriptionException {
        if (fullCommand.equals("find")) {
            throw new NoDescriptionException("Please specify a keyword to search up.");
        }

        String[] splitCommand = fullCommand.split(" ", 2);
        keyword = splitCommand[1];
    }

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
