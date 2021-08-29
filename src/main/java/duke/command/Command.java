package duke.command;

import java.io.FileNotFoundException;

import duke.ResponseLogic;
import duke.Storage;
import duke.task.TaskList;

public abstract class Command {

    /**
     * Executes the current command.
     *
     * @param tasks The TaskList object containing the list of tasks.
     * @param responseLogic The ResponseLogic object of the current Duke object.
     * @param storage The Storage object of the current Duke object.
     * @return The chatbot's response as a result of executing the command.
     * @throws FileNotFoundException If the file containing the tasks is not found.
     */
    public abstract String execute(
            TaskList tasks, ResponseLogic responseLogic, Storage storage) throws FileNotFoundException;

    /**
     * Returns true if the program should exit.
     *
     * @return Exit boolean of the Duke program.
     */
    public boolean isExit() {
        return false;
    }
}
