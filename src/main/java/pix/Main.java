package pix;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pix.javafx.MainWindow;


/**
 * A GUI for Pix using FXML.
 */
public class Main extends Application {

    private final Pix pix = new Pix("./data/Pix.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Pix");
            fxmlLoader.<MainWindow>getController().setPix(pix);
            // @@author CheyanneSim-reused
            stage.setOnCloseRequest(event -> {
                event.consume();
                fxmlLoader.<MainWindow>getController().closeWindowButtonClicked();
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
