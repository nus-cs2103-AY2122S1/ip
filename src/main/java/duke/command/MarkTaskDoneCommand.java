package duke.command;

import duke.exceptions.InvalidTaskNumberException;
import duke.io.UserOutputHandler;
import duke.messages.Message;
import duke.messages.MessageConstants;
import duke.messages.TaskDoneMessage;
import duke.tasks.Task;
import duke.tasks.TaskList;

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
            userOutputHandler.writeMessage(new Message(MessageConstants.MESSAGE_INVALID_INTEGER));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
