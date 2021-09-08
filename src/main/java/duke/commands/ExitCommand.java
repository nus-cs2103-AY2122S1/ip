package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.ui.TextUi;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks) throws DukeException {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
            }
        }, 1000);
        return TextUi.showGoodbyeMessage();
    }
}
