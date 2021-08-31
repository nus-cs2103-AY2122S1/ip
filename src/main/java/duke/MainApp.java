package duke;

import duke.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX application that initialises Duke
 */
public class MainApp extends Application {

    private Ui ui;
    private Duke duke;

    /**
     * Initialises the UI and Duke
     */
    @Override
    public void init() {
        ui = new Ui();
        duke = new Duke("./data/saveFile.txt");
    }

    /**
     * Starts the UI.
     *
     * @param primaryStage JavaFX stage being used.
     */
    @Override
    public void start(Stage primaryStage) {
        ui.start(primaryStage, duke);
    }
}
