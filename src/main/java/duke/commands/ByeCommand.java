package duke.commands;

import java.util.Timer;
import java.util.TimerTask;

import duke.Main;
import duke.gui.Ui;
import duke.storage.Storage;

/**
 * Class to handle the bye command.
 * Taken / Adapted from:
 * https://stackoverflow.com/questions/21974415/how-to-close-
 * this-javafx-application-after-showing-a-message-in-a-text-area-elem/21996863.
 */
public class ByeCommand extends Command {

    /**
     * Exit the application by calling Main.
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        new Timer().schedule(new TimerTask() {
            public void run () {
                Main.exit();
            }
        }, 1000);
        return ui.greet(false);
    }
}
