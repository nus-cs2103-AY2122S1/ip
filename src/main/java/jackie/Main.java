package jackie;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Jackie using FXML.
 */
public class Main extends Application {
    private Jackie jackie = new Jackie(Paths.get(new File("").getAbsolutePath(), "data", "jackie.txt"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle("Jackie - Your Most Reliable Helper");
            stage.getIcons().add(new Image("/images/DaJackie.png"));
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJackie(jackie);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
