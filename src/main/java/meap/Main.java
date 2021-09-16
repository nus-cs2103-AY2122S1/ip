package meap;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import meap.controller.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Meap meap = new Meap();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindows.fxml"));
            AnchorPane ap = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setDuke(meap);

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
//            stage.initStyle(StageStyle.DECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


