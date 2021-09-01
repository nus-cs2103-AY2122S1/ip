package petal.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import petal.Petal;

/**
 * A GUI built for Petal
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

            /*This is done to ensure that Ui has an association to MainWindow and Petal,
              MainWindow has an association to Ui, and Petal has an association to Ui*/
            fxmlLoader.<MainWindow>getController().setUi(petal.getUi());

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
