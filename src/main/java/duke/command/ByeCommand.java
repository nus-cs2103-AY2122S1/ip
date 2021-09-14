package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents an terminate command
 */
public class ByeCommand extends Command {

    private static final int TIME_FOR_MESSAGE = 3000;

    public static final String COMMAND_WORD = "bye";
    public static final String USAGE_TEXT = "Usage: bye";

    /**
     * Saves tasks in given TaskList and displays final message.
     *
     * @param taskList List of tasks.
     * @param ui User interface.
     * @param storage Storage of tasks.
     * @throws DukeException If error occurs while saving tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.save(taskList);
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
        }
        // Schedule platform exit 3s in the future
        new Timer().schedule(new TimerTask() {
            public void run () { System.exit(0); }
        }, TIME_FOR_MESSAGE);
        return ui.showBye();
    }
}
