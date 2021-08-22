package duke.command;

import duke.exceptions.InvalidTaskNumberException;
import duke.io.UserOutputHandler;
import duke.messages.Message;
import duke.messages.MessageConstants;
import duke.messages.TaskDeleteMessage;
import duke.tasks.Task;
import duke.tasks.TaskList;

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
