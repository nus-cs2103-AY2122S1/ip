package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public ListCommand() {
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
