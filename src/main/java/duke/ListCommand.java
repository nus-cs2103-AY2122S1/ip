package duke;

import java.io.IOException;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showTaskList(tasks);
    }
}
