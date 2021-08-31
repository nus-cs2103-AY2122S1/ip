package duke;

import duke.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Ui ui;
    private Duke duke;

    @Override
    public void init() {
        ui = new Ui();
        duke = new Duke("./data/saveFile.txt");
    }


    @Override
    public void start(Stage primaryStage) {
        ui.start(primaryStage, duke);
    }
}
