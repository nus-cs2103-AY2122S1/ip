import java.io.IOException;

import captain.Captain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String FILEPATH = "./data/taskdata.txt";
    private Captain captain = new Captain(FILEPATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("CAPTain");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image("images/capt.png"));
            fxmlLoader.<MainWindow>getController().setCaptain(captain);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
