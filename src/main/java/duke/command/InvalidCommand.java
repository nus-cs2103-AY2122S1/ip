package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;

public class InvalidCommand extends Command {

    public InvalidCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public String execute(TaskList tasks, Storage storage) {
        return "Sorry I do not understand this directive.";
    }
}
