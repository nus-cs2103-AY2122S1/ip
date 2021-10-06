package duke.command;

import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The {@code Command} class represents a user input command.
 *
 * @author Elizabeth Chow
 */
public abstract class Command {
    protected String[] args;

    /**
     * Constructs a new {@code Command} class.
     */
    public Command() {
        this.args = null;
    }

    /**
     * Constructs a new {@code Command} class with the specified args.
     *
     * @param args An array of {@code String} required to construct a {@code Task}
     *             object.
     */
    public Command(String ... args) {
        this.args = args;
    }

    /**
     * Prints to the terminal of the status of operations performed by the
     * {@code Command}. Writes to the storage if tasks is modified.
     *
     * @param tasks   the tasklist to be modified.
     * @param ui      responsible for printing to the terminal.
     * @param storage stores all the tasks.
     *
     * @return String message to be displayed.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Indicates whether the program should exit after this {@code Command}.
     *
     * @return {@code true} if the program should exit, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    };

    @Override
    public boolean equals(Object other) {
        if (other instanceof Command) {
            return Arrays.equals(((Command) other).args, this.args);
        }
        return false;
    }
}
