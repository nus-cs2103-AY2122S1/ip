package eightbit.gui;

import java.io.IOException;

import eightbit.EightBit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final EightBit eightBit = new EightBit();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("EightBit");
            fxmlLoader.<MainWindow>getController().setEightBit(eightBit);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
