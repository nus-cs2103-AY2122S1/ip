package duke.command;

import duke.util.Storage;
import duke.task.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.respond(String.format(
                        "Ooh yeah! Here are your %d tasks\n%s",
                        taskList.getSize(),
                        taskList.list())
        );
    }
}
