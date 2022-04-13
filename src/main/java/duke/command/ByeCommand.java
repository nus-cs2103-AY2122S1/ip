package duke.command;

import java.util.Timer;
import java.util.TimerTask;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ByeCommand extends Command {

    private static final int PAUSE = 1500;

    /**
     * Returns a string representing Duke's response to a "bye" command and exits the GUI.
     *
     * @param tasks The taskList where all tasks are stored.
     * @param ui An instance of the Ui class that is responsible for Duke's user interactions.
     * @param storage An instance of a the Storage class that saves and loads Duke's data.
     * @return A string representing Duke's reply after executing this command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        assert storage != null;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, PAUSE);
        return ui.showExit();
    }
}

