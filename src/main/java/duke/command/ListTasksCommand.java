package duke.command;

import duke.Message;
import duke.TaskList;
import duke.storage.TaskStorage;
import duke.ui.Ui;

public class ListTasksCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, TaskStorage taskStorage) {
        String taskListMessage = Message.getTaskListMessage(taskList);
        ui.setCurrentMessage(taskListMessage);
    }
}
