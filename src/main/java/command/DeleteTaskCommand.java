package command;

import exceptions.InvalidTaskNumberException;
import io.UserOutputHandler;
import messages.Message;
import messages.MessageConstants;
import messages.TaskDeleteMessage;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;

public class DeleteTaskCommand extends Command {
    public DeleteTaskCommand(String getUserInputBody) {
        super(getUserInputBody);
    }

    @Override
    public void execute(UserOutputHandler userOutputHandler, TaskList taskList) throws InvalidTaskNumberException {
        try {
            // user input is 1 greater than index.
            int index = Integer.parseInt(super.getUserInputBody()) - 1;
            Task deletedTask = taskList.deleteTask(index);
            userOutputHandler.writeMessage(new TaskDeleteMessage(deletedTask.toString(), taskList.getNumOfTasks()));
        } catch (NumberFormatException | IOException nfe) {
            userOutputHandler.writeMessage(new Message(MessageConstants.INVALID_INTEGER_MESSAGE));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
