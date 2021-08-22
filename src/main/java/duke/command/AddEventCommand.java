package duke.command;

import duke.exceptions.EmptyEventBodyException;
import duke.exceptions.InvalidEventBodyException;
import duke.io.ConsoleUserOutputHandler;
import duke.io.UserOutputHandler;
import duke.messages.TaskAddMessage;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

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
