package command;

import exceptions.EmptyDeadlineBodyException;
import exceptions.InvalidDateTimeFormatException;
import exceptions.InvalidDeadlineBodyException;
import io.UserOutputHandler;
import messages.TaskAddMessage;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;

public class AddDeadlineCommand extends Command {

    public AddDeadlineCommand(String getUserInputBody) {
        super(getUserInputBody);
    }

    @Override
    public void execute(UserOutputHandler userOutputHandler, TaskList taskList)
            throws IOException, InvalidDateTimeFormatException,
            InvalidDeadlineBodyException, EmptyDeadlineBodyException {
        Task addedDeadline = taskList.addTask(new Deadline(super.getUserInputBody()));
        userOutputHandler.writeMessage(new TaskAddMessage(addedDeadline.toString(),
                taskList.getNumOfTasks()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
