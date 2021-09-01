import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Represents a GUI for Duke using FXML.
 */
public class Main extends Application {
    private final Duke duke = new Duke();

    /**
     * Populates application window with UI.
     *
     * @param stage current window.
     */
    @Override
    public void start(Stage stage) {
        try {
            MainWindow window = new MainWindow();
            window.setDuke(duke);

            Scene scene = new Scene(window);
            scene.getStylesheets().add("/view/stylesheet.css");

            stage.setScene(scene);
            stage.setTitle("Duke Chatterbot");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
