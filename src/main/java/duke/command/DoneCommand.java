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
    private final int index;

    /**
     * Constructor for the Done command.
     * @param duke Duke chatbot that is in use.
     * @param sc Scanner object that is in use.
     * @param index index of the task that is to be marked as done.
     */
    public DoneCommand(Duke duke, Scanner sc, int index) {
        super(duke, sc);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList) throws IOException {
        this.duke.setTaskAsDone(this.index);
        Ui.printDoneMessage(taskList.getTask(this.index - 1));
        duke.saveTasks();
    }
}

