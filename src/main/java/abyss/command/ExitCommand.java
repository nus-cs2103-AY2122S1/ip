package abyss.command;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand implements Command {
    private static final String EXIT_MESSAGE = "Exiting the Abyss. We look forward to your return.";

    protected ExitCommand() {}

    /**
     * Executes the exit command.
     *
     * @return Response from executing the exit command.
     */
    public String execute() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, 1500);
        return EXIT_MESSAGE;
    }
}

