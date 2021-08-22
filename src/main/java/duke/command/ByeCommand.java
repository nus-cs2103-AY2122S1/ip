package duke.command;

import duke.io.UserOutputHandler;
import duke.messages.ByeMessage;
import duke.tasks.TaskList;

public class ByeCommand extends Command {
    public ByeCommand(String userInputBody) {
        super(userInputBody);
    }

    @Override
    public void execute(UserOutputHandler userOutputHandler, TaskList taskList) {
        userOutputHandler.writeMessage(new ByeMessage());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
