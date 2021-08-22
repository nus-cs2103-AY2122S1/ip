package duke.command;

import duke.io.ConsoleUserOutputHandler;
import duke.io.UserOutputHandler;
import duke.messages.TaskListMessage;
import duke.tasks.TaskList;

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
