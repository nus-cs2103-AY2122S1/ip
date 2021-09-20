package biscuit.ui;

import java.io.IOException;
import java.util.List;

import biscuit.Biscuit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Biscuit using FXML.
 */
public class Main extends Application {

    private String defaultPath = "data/biscuit.csv";

    /**
     * Starts the Biscuit JavaFX application.
     *
     * @param stage Root Stage object.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Get command-line argument for file path of save file
            List<String> params = getParameters().getRaw();
            if (params.size() == 1) {
                defaultPath = params.get(0);
            }
            assert !defaultPath.trim().isEmpty() : "File path cannot be empty";
            assert defaultPath.endsWith(".csv") : "File type must be .csv";
            Biscuit biscuit = new Biscuit(defaultPath);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBiscuit(biscuit);
            stage.setTitle("Biscuit - Track your tasks today!");
            stage.getIcons().add(new Image("/images/PawIcon.png"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
