package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a Command given by the user.
 */
public abstract class Command {

    /**
     * Executes the action for this command.
     *
     * @param tasks   The task list to execute the command on.
     * @param storage The storage for the tasks.
     * @return a string output.
     * @throws DukeException
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Returns true if the command causes program to exit.
     *
     * @return true if command is exit command.
     */
    public abstract boolean isExit();

    /**
     * Appends multiple strings into one string separated by newline character.
     *
     * @param outputs String outputs
     * @return A single string output
     */
    protected String formatOutput(String... outputs) {
        return String.join("\n", outputs);
    }

    /**
     * Appends multiple strings in an array into one string separated by newline character.
     *
     * @param outputs String outputs.
     * @return A single string output.
     */
    protected String formatOutput(ArrayList<String> outputs) {
        return String.join("\n", outputs);
    }

    /**
     * Wraps the output string in triple back ticks to indicate code block.
     *
     * @param output String output.
     * @return A string output in code black.
     */
    protected String formatCodeBlockOutput(String output) {
        return "```\n" + output + "\n```";
    }
}
