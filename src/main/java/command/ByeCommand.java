package command;

import io.UserOutputHandler;
import messages.ByeMessage;
import tasks.TaskList;

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
