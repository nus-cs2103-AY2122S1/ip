package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class SearchCommand extends Command {
    private int type;
    private String response;

    public SearchCommand(String response, int type) {
        this.response = response;
        this.type = type;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList currList = new TaskList();
        switch (type) {
        case 1:
            String preTime = response.substring(5);
            String actualTime = Task.dateAndTime(preTime);
            currList = tasks.tasksWithDate(actualTime);
            break;
        case 2:
            String content = response.substring(5);
            currList = tasks.tasksWithContent(content);
            break;
        }
        ui.showList(currList, currList.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
