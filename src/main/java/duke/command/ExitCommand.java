package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 *  Represents a Command that Exits the Chatbot Program
 */

public class ExitCommand extends Command {

    /**
     * Executes the Exit Command.
     *
     * @param tasks TaskList to write into Storage.
     * @param ui UI to reflect the exit.
     * @param storage Storage to write the TaskList into.
     * @throws IOException On output Error.
     * @see IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.write(tasks);
        return ui.printExit();
    }

    /**
     * Reflects that it is an ExitCommand
     * @return true
     */

    public boolean isExit() {
        return true;
    }

}