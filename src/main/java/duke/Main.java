package duke;

import controller.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow(duke);
        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.show();
    }
}
