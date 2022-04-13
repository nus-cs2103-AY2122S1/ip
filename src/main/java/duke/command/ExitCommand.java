package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class ExitCommand extends Command {

    /**
     * Executes the specified command.
     *  @param tasks The TaskList which we are modifying.
     * @param ui The Ui we will use for user interaction.
     * @param storage The Storage we will use for storing save data.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        new Timer().schedule(new TimerTask() {
            public void run () { Platform.exit(); System.exit(0); }
        }, 1000);
        return storage.write(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
