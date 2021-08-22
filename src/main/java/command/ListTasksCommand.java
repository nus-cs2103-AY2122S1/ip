package command;

import io.UserOutputHandler;
import messages.TaskListMessage;
import tasks.TaskList;

public class ListTasksCommand extends Command {

    public ListTasksCommand(String userInputBody) {
        super(userInputBody);
    }

    @Override
    public void execute(UserOutputHandler userOutputHandler, TaskList taskList) {
        userOutputHandler.writeMessage(new TaskListMessage(taskList.getAllTasks()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
