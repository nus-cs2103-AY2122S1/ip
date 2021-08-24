package duke.command;

import duke.Duke;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the general delete command.
 */
public class DeleteCommand extends Command {
    private final int INDEX;

    public DeleteCommand(Duke duke, Scanner sc, int index) {
        super(duke, sc);
        this.INDEX = index;
    }

    @Override
    public void execute(TaskList taskList) throws IOException {
        Task taskRemoved = this.duke.deleteTask(this.INDEX);
        Ui.printDeleteTaskMessage(taskRemoved, taskList.getTotal());
        this.duke.saveTasks();
    }
}
