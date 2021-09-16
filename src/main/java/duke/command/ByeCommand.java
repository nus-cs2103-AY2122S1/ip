package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;

public class ByeCommand extends Command {

    public static final String BYE_MESSAGE = "Bye";

    public ByeCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public String execute(TaskList tasks, Storage storage) {
        return BYE_MESSAGE;
    }
}
