package sariel.util.windows;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sariel.util.commons.Messages;
import sariel.util.controller.Sariel;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static final String saveFilePath = "save.txt";
    private static final String tempFilePath = "temp.txt";
    private Sariel sariel;

    @Override
    public void start(Stage stage) {
        this.sariel = new Sariel(saveFilePath, tempFilePath, stage);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(600);
            stage.setMinWidth(780);
            stage.getIcons().add(Messages.ICON);
            fxmlLoader.<MainWindow>getController().setSariel(sariel);
            TaskInputWindow.setDuke(sariel);
            stage.setTitle("Sariel");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
