package duke.command;

import duke.util.Ui;

/**
 * This class encapsulates the command dealing with exiting Duke.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class ExitCommand extends Command {
    public ExitCommand() {
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getResponse(String input) {
        return Ui.getExitMessage();
    }
}
