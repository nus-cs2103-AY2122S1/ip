package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class DoneCommand implements Command {

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (infos.length == 2) {
            String index = infos[1];
            return doTask(index, taskList);
        } else {
            return "Wrong input format";
        }
    }
}
