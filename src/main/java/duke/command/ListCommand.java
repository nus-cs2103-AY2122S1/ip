package duke.command;

import duke.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {

    }

    @Override
    public void execute(TaskList taskList){
        Ui.showTaskList(taskList);
    }

    @Override
    public String getType() {
        return "list";
    }

}
