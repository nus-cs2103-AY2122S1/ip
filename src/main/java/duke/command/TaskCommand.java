package duke.command;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the general task command.
 */
public class TaskCommand extends Command {
    private final String DESCRIPTION;

    public TaskCommand(Duke duke, Scanner sc, String description) {
        super(duke, sc);
        this.DESCRIPTION = description;
    }

    @Override
    public void execute(TaskList taskList) throws IOException, DukeException {
        Ui.printAddedTaskMessage(this.duke.addTaskToList(DESCRIPTION), taskList.getTotal());
        this.duke.saveTasks();
    }
}
