package command;

import exceptions.DukeException;
import exceptions.EmptyEventBodyException;
import exceptions.InvalidEventBodyException;
import io.UserOutputHandler;
import messages.TaskAddMessage;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;

public class AddEventCommand extends Command {

    public AddEventCommand(String getUserInputBody) {
        super(getUserInputBody);
    }

    @Override
    public void execute(UserOutputHandler userOutputHandler, TaskList taskList)
            throws IOException, EmptyEventBodyException, InvalidEventBodyException {
        Task addedTask = taskList.addTask(new Event(super.getUserInputBody()));
        userOutputHandler.writeMessage(new TaskAddMessage(addedTask.toString(),
                taskList.getNumOfTasks()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
