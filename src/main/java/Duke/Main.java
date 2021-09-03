package Duke;

import Duke.Ui.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) throws IOException {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            scene.getStylesheets().add("Stytle.css");
            stage.setScene(scene); // Setting the stage to show our screen
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show(); // Render the stage.
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
