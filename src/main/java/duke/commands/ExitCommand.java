package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents an ExitCommand that will exit the program when executed.
 *
 * @author ruiquan
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the ExitCommand and exit the program.
     *
     * @param tasks The collection of tasks.
     * @param ui The user interface that handles input and output.
     * @param storage The storage manager that deals with loading from and
     *               saving into a file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = "Goodbye, hope to see you again soon!";
        ui.reply(message);
    }
}
