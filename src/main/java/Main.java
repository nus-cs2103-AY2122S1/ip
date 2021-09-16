import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Represents a GUI for Duke using FXML.
 */
public class Main extends Application {
    private final Duke duke = new Duke();
    private final Ui ui = new Ui();

    /**
     * Populates application window with UI.
     *
     * @param stage current window.
     */
    @Override
    public void start(Stage stage) {
        try {
            MainWindow window = new MainWindow();
            window.setDuke(duke, ui);

            Scene scene = new Scene(window);
            scene.getStylesheets().add("/view/stylesheet.css");

            stage.setScene(scene);
            stage.setTitle("Jarvis Chatterbot");
            stage.show();

            window.welcomeMessage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
