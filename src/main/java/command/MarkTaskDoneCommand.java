package command;

import exceptions.InvalidTaskNumberException;
import io.UserOutputHandler;
import messages.Message;
import messages.MessageConstants;
import messages.TaskDoneMessage;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;

public class MarkTaskDoneCommand extends Command {
    public MarkTaskDoneCommand(String userInputBody) {
        super(userInputBody);
    }

    @Override
    public void execute(UserOutputHandler userOutputHandler, TaskList taskList) throws InvalidTaskNumberException {
        try {
            // user input is 1 greater than index.
            int index = Integer.parseInt(super.getUserInputBody()) - 1;
            Task doneTask = taskList.setDone(index);
            userOutputHandler.writeMessage(new TaskDoneMessage(doneTask));
        } catch (NumberFormatException | IOException nfe) {
            userOutputHandler.writeMessage(new Message(MessageConstants.INVALID_INTEGER_MESSAGE));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
