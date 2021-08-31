package duke.command;

import duke.FileController;
import duke.tasks.TaskList;

public class PrintListCommand extends Command {
    public String execute(TaskList tasks, FileController fc) {
        return tasks.toString();
    }
}
