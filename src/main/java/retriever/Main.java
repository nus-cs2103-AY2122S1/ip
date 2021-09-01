package retriever;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Retriever retriever = new Retriever();

    /**
     * Starts the GUI interface.
     * @param stage The window shown.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // Can modify this line for changing font
            scene.getRoot().setStyle("-fx-font-family: 'Courier New'");

            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRetriever(retriever);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
