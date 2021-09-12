package bot.utility;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Simulates the Duke chatBot.
 */
public class IntelliBot extends Application {
    private Parser parser;
    private Ui ui;

    /**
     * Creates a Duke.
     */
    public void init() {
        TaskList.initialize();
        Logger.initialize();
        parser = new Parser();
        ui = new Ui();
    }

    /**
     * Sets up the GUI and the DukeBot functionality.
     *
     * @param stage The Stage to be used for the GUI.
     */
    @Override
    public void start(Stage stage) {
        init();
        ui.start(stage, parser);
    }
}
