package calico.gui;

import java.io.IOException;

import calico.Calico;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Calico calico = new Calico("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Calico");
            stage.setResizable(false);
            scene.setFill(Color.TRANSPARENT);
            fxmlLoader.<MainWindow>getController().setCalico(calico);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
