package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.io.IOException;

/**
 * Represents a command to exit the chatbot.
 * 
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class ExitCommand extends Command {
    private boolean isExitCommand;

    /**
     * Class constructor.
     */
    public ExitCommand() {
        isExitCommand = true;
    }

    /**
     * Executes the command to exit the chat bot, stores tasks into storage.
     * 
     * @param tasks lists of tasks
     * @param ui the user interface.
     * @param storage the storage file.
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.store(tasks.getTasks());
            ui.showFarewell();
        } catch (IOException e) {
            throw new DukeException(e);
        }
    }

    /**
     * Checks if this command is an exit command.
     * @return true.
     */
    public boolean isExit() {
        return isExitCommand;
    }
}
