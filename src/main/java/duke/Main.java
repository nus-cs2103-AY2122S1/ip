package duke;

import controller.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Encapsulates main class that starts the application with duke and the user interface.
 */
public class Main extends Application {
    private final Duke duke = new Duke();

    /**
     * Starts the application.
     *
     * @param stage Stage for the user interface.
     */
    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow(this.duke);
        Scene scene = new Scene(mainWindow);

        stage.setScene(scene);
        stage.setTitle("Duke");
        stage.setResizable(false);

        stage.show();
    }
}
