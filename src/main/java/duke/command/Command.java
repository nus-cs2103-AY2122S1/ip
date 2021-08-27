package duke.command;

import java.io.IOException;

import duke.exception.NoSuchTaskException;
import duke.util.Storage;
import duke.util.TaskList;

/** An abstract class representing the user's commands. */
public abstract class Command {
    /**
     * Executes the Command.
     *
     * @param tasks The list of tasks in the program.
     * @param storage The storage utility.
     * @throws IOException If there is an IO exception.
     * @throws NoSuchTaskException If no tasks are found.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws IOException, NoSuchTaskException;

    /** Returns true if the Command is an exit command, else false.*/
    public abstract boolean isExit();

    /**
     * Formats the output from executing the commands.
     *
     * @param output The output from executing the commands.
     * @return A properly formatted string.
     */
    protected static String outputFormatter(String prefix, String...output) {
        StringBuilder res = new StringBuilder(prefix).append("\n");
        for (String out : output) {
            res.append(out).append("\n");
        }
        return res.toString().trim();
    }
}
