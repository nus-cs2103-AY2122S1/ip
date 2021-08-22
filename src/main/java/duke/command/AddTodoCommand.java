package duke.command;

import duke.exceptions.EmptyTodoBodyException;
import duke.io.UserOutputHandler;
import duke.messages.TaskAddMessage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

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
