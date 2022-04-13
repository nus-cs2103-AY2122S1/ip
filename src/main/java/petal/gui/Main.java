package petal.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import petal.Petal;

/**
 * Main class for the Petal's GUI
 */
public class Main extends Application {

    //The petal instance to be used
    private final Petal petal = new Petal();

    /**
     * Starts the GUI for Petal
     *
     * @param stage The stage to be used
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            //This is done to ensure that Ui is bidirectional to both Petal, and MainWindow
            fxmlLoader.<MainWindow>getController().setUi(petal.getUi());

            //Set title and icon of the window
            stage.getIcons().add(new Image("/images/picture.png"));
            stage.setTitle("Petal");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
