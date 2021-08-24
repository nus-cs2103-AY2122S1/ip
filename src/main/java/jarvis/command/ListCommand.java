package jarvis.command;

import jarvis.exception.JarvisException;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws JarvisException {
        if (taskList.getTaskListSize() == 0) {
            throw new JarvisException("Your list is currently empty! Try adding some tasks.");
        }
        ui.showTaskList(taskList);
    }
}
