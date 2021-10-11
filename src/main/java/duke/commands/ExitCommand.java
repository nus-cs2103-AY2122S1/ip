package duke.commands;

import java.util.Timer;
import java.util.TimerTask;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import javafx.application.Platform;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        new Timer().schedule(new TimerTask() {
            public void run () {
                Platform.exit();
                System.exit(0); }
        }, 2000);
        return ui.showBye();
    }
}
