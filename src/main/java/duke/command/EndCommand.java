package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import javafx.application.Platform;

/**
 * Class that implements command. This class is responsible for ending the application.
 */
public class EndCommand implements Command {

    /**
     * Method to execute the given command.
     *
     * @param t The TaskList loaded.
     * @param ui The Ui object running the program.
     * @param storage The Storage object handling the loading and saving.
     * @throws DukeException Thrown in the event of input format errors.
     */
    @Override
    public String execute(TaskList t, Ui ui, Storage storage) {
        Platform.exit();
        System.exit(0);
        return ui.textFrame("Goodbye from Bob's list adder!");
    }

    /**
     * Method to determine if the program should continue running.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isRunning() {
        return false;
    }
}
