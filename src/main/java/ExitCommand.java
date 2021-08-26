package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

import java.io.IOException;

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

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.write(tasks);
        ui.printExit();
    }

    /**
     * Reflects that it is an ExitCommand
     * @return true
     */

    public boolean isExit() {
        return true;
    }

}