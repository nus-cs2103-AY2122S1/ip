package duke.commands;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

/**
 * Represents an Exit Command.
 */
public class ExitCommand extends Command {

    /** Unique command word */
    public static final String COMMAND_WORD = "bye";

    /** Message for exit */
    public static final String MESSAGE_EXIT = "Exiting Duke. See you again!";

    /** Empty constructor */
    public ExitCommand() {
    }

    /**
     * Executes the task and returns a CommandResult.
     *
     * @return CommandResult.
     */
    @Override
    public CommandResult execute() {
        // Schedules a Timer to exit the javafx Platform.
        // Code adapted from https://stackoverflow.com/questions/21974415/how-to-close-this-javafx-application-after-showing-a-message-in-a-text-area-elem/21996863
        new Timer().schedule(new TimerTask() {
            public void run() {
                Platform.exit();
            }
        }, 5000);

        return new CommandResult(MESSAGE_EXIT);
    }
}
