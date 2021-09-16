package daisy.command;

import java.util.Timer;
import java.util.TimerTask;

import daisy.task.Storage;
import daisy.task.TaskList;
import javafx.application.Platform;

/**
 * ExitCommand class handles the 'bye' command to close Duke.
 */
public class ExitCommand extends Command {

    public static final String BYE_MESSAGE = "Goodbye! Hope to see you again soon!\n";

    /**
     * Returns the exit message.
     *
     * @param taskList The TaskList of Daisy.
     * @param storage The Storage of Daisy.
     * @return Response string.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        //@@author tsy24-reused
        //Reused from https://stackoverflow.com/a/21996863
        new Timer().schedule(new TimerTask() {
            public void run() {
                Platform.exit();
            }
        }, 1500);
        return BYE_MESSAGE;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }
}
