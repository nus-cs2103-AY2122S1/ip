package iris;

import java.util.TimerTask;

import javafx.application.Platform;

/**
 * Class to close the application upon ExitCommand.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class ExitProgram extends TimerTask {
    public void run() {
        Platform.exit();
    }
}
