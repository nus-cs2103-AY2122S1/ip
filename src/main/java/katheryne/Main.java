package katheryne;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Katheryne katheryne = new Katheryne();

    @Override
    public void start(Stage stage) {
        try {
            String pathToFxmlLoader = "/view/MainWindow.fxml";
            assert Files.isReadable(Paths.get(pathToFxmlLoader)): "fxml file must exist";
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(pathToFxmlLoader));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKatheryne(katheryne);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
