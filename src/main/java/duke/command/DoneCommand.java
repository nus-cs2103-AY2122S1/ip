package duke.command;

import duke.Duke;
import duke.task.TaskList;
import duke.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the general done command.
 */
public class DoneCommand extends Command {
    private final int INDEX;

    public DoneCommand(Duke duke, Scanner sc, int index) {
        super(duke, sc);
        this.INDEX = index;
    }

    @Override
    public void execute(TaskList taskList) throws IOException {
        this.duke.setTaskAsDone(this.INDEX);
        Ui.printDoneMessage(taskList.getTask(this.INDEX - 1));
        duke.saveTasks();
    }
}

