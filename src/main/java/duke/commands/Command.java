package duke.commands;

import duke.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

import java.util.ArrayList;

/**
 * Represents an executable command
 */
public abstract class Command {
    private boolean isActive = true;

    public Command() {
    }

    /**
     * Executes the command and returns the result.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isActive() {
        return this.isActive;
    }

    /**
     * Exits the program.
     */
    public void exit() {
        this.isActive = false;
    }

    /**
     * Constructs a message containing all tasks in the ArrayList.
     *
     * @param ArrayList<Task> lis of tasks
     * @return ArrayList in String format
     */
    public static String arrayToString(ArrayList<Task> list) {
        String answer = "";
        int counter = 1;
        for (Task item : list) {
            answer += String.format("%d: %s\n", counter, item.toString());
            counter++;
        }
        return answer;
    }
}
