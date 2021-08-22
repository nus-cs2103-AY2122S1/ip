package duke.command;

import duke.exceptions.EmptyDeadlineBodyException;
import duke.exceptions.InvalidDateTimeFormatException;
import duke.exceptions.InvalidDeadlineBodyException;
import duke.io.ConsoleUserOutputHandler;
import duke.io.UserOutputHandler;
import duke.messages.TaskAddMessage;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;

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
