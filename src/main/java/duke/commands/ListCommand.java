package duke.commands;

import duke.Ui;
import duke.tasks.TaskList;

public class ListCommand extends Command {

    @Override
    public void executeCommand(TaskList taskList) {
        Ui.displayList(taskList);
    }
}
