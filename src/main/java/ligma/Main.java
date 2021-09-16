package ligma;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ligma.ui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Ligma ligma;


    @Override
    public void start(Stage stage) {
        try {
            ligma = new Ligma("ligmaData/tasks.txt");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            scene.getRoot().setStyle("-fx-font-family: 'Arial'");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Ligma");

            fxmlLoader.<MainWindow>getController().setLigma(ligma);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}