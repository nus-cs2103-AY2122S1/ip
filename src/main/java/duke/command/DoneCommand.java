package duke.command;

import duke.Duke;
import duke.task.TaskList;
import duke.GUI;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the general done command.
 */
public class DoneCommand extends Command {
    private final int INDEX;

    /**
     * Constructor for the Done command.
     * @param duke Duke chatbot that is in use.
     * @param index index of the task that is to be marked as done.
     */
    public DoneCommand(Duke duke, int index) {
        super(duke);
        this.INDEX = index;
    }

    @Override
    public String execute(TaskList taskList) throws IOException {
        this.duke.setTaskAsDone(this.INDEX);
        this.duke.saveTasks();

        return GUI.printDoneMessage(taskList.getTask(this.INDEX - 1));
    }
}

