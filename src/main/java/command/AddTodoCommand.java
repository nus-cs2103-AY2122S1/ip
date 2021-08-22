package command;

import exceptions.EmptyTodoBodyException;
import io.UserOutputHandler;
import messages.TaskAddMessage;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;

import java.io.IOException;

public class AddTodoCommand extends Command {

    public AddTodoCommand(String getUserInputBody) {
        super(getUserInputBody);
    }

    @Override
    public void execute(UserOutputHandler userOutputHandler, TaskList taskList)
            throws IOException, EmptyTodoBodyException {
        Task addedToDo = taskList.addTask(new ToDo(super.getUserInputBody()));
        userOutputHandler.writeMessage(new TaskAddMessage(addedToDo.toString(),
                taskList.getNumOfTasks()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
