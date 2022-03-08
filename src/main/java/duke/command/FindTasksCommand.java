package duke.command;

import duke.Message;
import duke.TaskList;
import duke.storage.TaskStorage;
import duke.ui.Ui;

public class FindTasksCommand extends Command {
    private String query;

    public FindTasksCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, TaskStorage taskStorage) {
        TaskList filteredTaskList = taskList.getFilteredTaskList(query);

        String filteredTaskListMessage = Message.getFilteredTaskListMessage(filteredTaskList);
        ui.setCurrentMessage(filteredTaskListMessage);
    }

}
