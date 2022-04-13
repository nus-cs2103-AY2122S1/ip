package duke.command;

import java.util.Timer;
import java.util.TimerTask;

import duke.ui.Ui;
import javafx.application.Platform;

/**
 * Command to exit program.
 */
public class CommandExit extends Command {

    /**
     * Constructor for this command.
     */
    public CommandExit() {
        this.commandName = "gubbai";
        this.description = "Exits the program";
        this.arguments = new String[]{};
    }

    /**
     * Terminates the program after 2 seconds and prints the goodbye message.
     */
    @Override
    public String execute() {
        new Timer().schedule(new TimerTask() {
            public void run () {
                Platform.exit();
            }
        }, 2000);

        return Ui.goodByeMessage();
    }
}
