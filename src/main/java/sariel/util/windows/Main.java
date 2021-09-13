package duke.util.windows;

import java.io.IOException;

import duke.util.commons.Messages;
import duke.util.controller.Sariel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static final String saveFilePath = "save.txt";
    private static final String tempFilePath = "temp.txt";
    private Sariel sariel = new Sariel(saveFilePath, tempFilePath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(590);
            stage.setMinWidth(770);
            stage.getIcons().add(Messages.ICON);
            fxmlLoader.<MainWindow>getController().setSariel(sariel);
            TaskInputWindow.setDuke(sariel);
            stage.setTitle("Sariel");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
