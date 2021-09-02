package duke.command;

import duke.Storage;
import duke.TaskList;

public class ByeCommand extends Command {

    public ByeCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public String execute(TaskList tasks, Storage storage) {
        return "Bye";
    }
}
